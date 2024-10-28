package be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence.warehouse;

import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.MaterialTypeEntity;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEntity;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEventEntity;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEventsWindowEntity;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence.Repository.WarehouseEventEntityRepository;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence.Repository.WarehouseEventsWindowEntityRepository;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence.Repository.WarehouseRepository;
import be.kdg.prog6.warehouseBoundedContext.domain.*;
import be.kdg.prog6.warehouseBoundedContext.port.out.Warehouse.WarehouseLoadPort;
import be.kdg.prog6.warehouseBoundedContext.port.out.Warehouse.WarehouseSavePort;
import util.errorClasses.WarehouseDatabaseException;
import util.errorClasses.WarehouseNotFoundException;
import domain.MaterialType;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class WarehouseDataBaseAdapter implements WarehouseLoadPort, WarehouseSavePort {

    private final WarehouseRepository warehouseRepository;
    private WarehouseEventEntityRepository warehouseEventEntityRepository;
    private WarehouseEventsWindowEntityRepository warehouseEventsWindowEntityRepository;
    private final ModelMapper modelMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseDataBaseAdapter.class);

    public WarehouseDataBaseAdapter(@Qualifier("warehouse") ModelMapper modelMapper, WarehouseEventsWindowEntityRepository warehouseEventsWindowEntityRepository, WarehouseEventEntityRepository warehouseEventEntityRepository, WarehouseRepository warehouseRepository) {
        this.modelMapper = modelMapper;
        this.warehouseEventsWindowEntityRepository = warehouseEventsWindowEntityRepository;
        this.warehouseEventEntityRepository = warehouseEventEntityRepository;
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public Warehouse fetchWarehouseWithEvents(UUID warehouseId) {
        try {
            WarehouseEntity entity = warehouseRepository.fetchWarehouseWithEvents(warehouseId);
            if (entity == null) {
                throw new WarehouseNotFoundException("Warehouse not found with ID: " + warehouseId);
            }
            return modelMapper.map(entity, Warehouse.class);
        } catch (Exception e) {
            LOGGER.error("Error fetching warehouse with events for ID: {}", warehouseId, e);
            throw new WarehouseDatabaseException("Database error: Could not fetch warehouse with events", e);
        }
    }

    @Override
    public Warehouse findBySellerId(SellerId sellerId) {
        try {
            WarehouseEntity entity = warehouseRepository.findBySellerId(sellerId);
            if (entity == null) {
                throw new WarehouseNotFoundException("Warehouse not found for seller ID: " + sellerId);
            }
            return modelMapper.map(entity, Warehouse.class);
        } catch (Exception e) {
            LOGGER.error("Error finding warehouse by seller ID: {}", sellerId, e);
            throw new WarehouseDatabaseException("Database error: Could not find warehouse by seller ID", e);
        }
    }

    @Override
    public Warehouse findBySellerIdAndWarehouseId(SellerId sellerId, UUID warehouseId) {
        try {
            WarehouseEntity entity = warehouseRepository.findBySellerIdAndWarehouseId(sellerId, warehouseId);
            if (entity == null) {
                throw new WarehouseNotFoundException("Warehouse not found for seller ID: " + sellerId + " and warehouse ID: " + warehouseId);
            }
            return modelMapper.map(entity, Warehouse.class);
        } catch (Exception e) {
            LOGGER.error("Error finding warehouse by seller ID: {} and warehouse ID: {}", sellerId, warehouseId, e);
            throw new WarehouseDatabaseException("Database error: Could not find warehouse by seller ID and warehouse ID", e);
        }
    }

    @Override
    public Warehouse findBySellerIdAndMaterialType(SellerId sellerId, MaterialType materialType) {
        try {
            WarehouseEntity warehouseEntity = warehouseRepository.findBySellerIdAndMaterialType(sellerId.sellerID(), materialType);
            if (warehouseEntity == null) {
                throw new WarehouseNotFoundException("Warehouse not found for seller ID: " + sellerId + " and material type: " + materialType);
            }

            WarehouseId warehouseId = new WarehouseId(warehouseEntity.getWarehouseId());
            SellerId domainSellerId = new SellerId(warehouseEntity.getSellerId());
            MaterialType domainMaterialType = MaterialType.valueOf(warehouseEntity.getMaterialType().toString());

            Warehouse warehouse = new Warehouse(warehouseId, domainSellerId, domainMaterialType);

            WarehouseEventsWindowEntity eventsWindowEntity = warehouseEntity.getWarehouseEventsWindow();
            if (eventsWindowEntity != null) {
                List<WarehouseEvent> warehouseEvents = eventsWindowEntity.getWarehouseEventList().stream()
                        .map(eventEntity -> new WarehouseEvent(
                                new WarehouseEventId(eventEntity.getEventId()),
                                eventEntity.getEventTime(),
                                EventType.valueOf(eventEntity.getEventType()),
                                eventEntity.getMaterialWeight(),
                                eventEntity.getWeighBridgeTicketId(),
                                eventsWindowEntity.getWarehouseEventsWindowId(),
                                MaterialType.valueOf(eventEntity.getMaterialType().toString())
                        ))
                        .collect(Collectors.toList());

                WarehouseEventsWindow eventsWindow = new WarehouseEventsWindow(
                        warehouseId,
                        eventsWindowEntity.getWarehouseEventsWindowId(),
                        warehouseEvents
                );

                warehouse.setEventsWindow(eventsWindow);
            }
            return warehouse;
        } catch (Exception e) {
            LOGGER.error("Error finding warehouse by seller ID: {} and material type: {}", sellerId, materialType, e);
            throw new WarehouseDatabaseException("Database error: Could not find warehouse by seller ID and material type", e);
        }
    }

    @Override
    @Transactional
    public void save(Warehouse warehouse, WarehouseEvent ignore) {
        try {
            WarehouseEntity warehouseEntity = warehouseRepository.findById(warehouse.getWarehouseNumber().getId()).orElse(new WarehouseEntity());
            warehouseEntity.setWarehouseId(warehouse.getWarehouseNumber().getId());
            warehouseEntity.setMaterialType(MaterialTypeEntity.valueOf(warehouse.getMaterialType().toString()));
            warehouseEntity.setSellerId(warehouse.getSellerId().sellerID());

            WarehouseEventsWindowEntity eventsWindowEntity = warehouseEventsWindowEntityRepository.findById(warehouse.getEventsWindow().getWarehouseEventsWindowId()).orElse(new WarehouseEventsWindowEntity());
            eventsWindowEntity.setWarehouseEventsWindowId(warehouse.getEventsWindow().getWarehouseEventsWindowId());
            eventsWindowEntity.setWarehouseId(warehouseEntity.getWarehouseId());

            List<WarehouseEventEntity> eventEntities = warehouse.getEventsWindow().getWarehouseEventList().stream()
                    .map(event -> new WarehouseEventEntity(
                            event.getId() != null ? event.getId().getId() : UUID.randomUUID(),
                            event.getTime(),
                            event.getType().toString(),
                            event.getMaterialWeight(),
                            event.getWeighBridgeTicketId(),
                            eventsWindowEntity,
                            MaterialTypeEntity.valueOf(warehouse.getMaterialType().toString())
                    ))
                    .toList();

            eventsWindowEntity.getWarehouseEventList().clear();
            eventsWindowEntity.getWarehouseEventList().addAll(eventEntities);

            warehouseEntity.setWarehouseEventsWindow(eventsWindowEntity);

            warehouseRepository.save(warehouseEntity);
        } catch (Exception e) {
            LOGGER.error("Error saving warehouse: {}", warehouse.getWarehouseNumber().getId(), e);
            throw new WarehouseDatabaseException("Database error: Could not save warehouse", e);
        }
    }


}

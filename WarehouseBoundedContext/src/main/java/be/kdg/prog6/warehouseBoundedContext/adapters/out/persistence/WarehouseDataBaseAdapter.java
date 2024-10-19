package be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence;

import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEntity;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEventEntity;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEventsWindowEntity;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence.Repository.WarehouseEventEntityRepository;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence.Repository.WarehouseEventsWindowEntityRepository;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence.Repository.WarehouseRepository;
import be.kdg.prog6.warehouseBoundedContext.domain.*;
import be.kdg.prog6.warehouseBoundedContext.port.out.Warehouse.WarehouseLoadPort;
import be.kdg.prog6.warehouseBoundedContext.port.out.Warehouse.WarehouseSavePort;
import domain.MaterialType;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
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

    public WarehouseDataBaseAdapter(@Qualifier("warehouse")ModelMapper modelMapper, WarehouseEventsWindowEntityRepository warehouseEventsWindowEntityRepository, WarehouseEventEntityRepository warehouseEventEntityRepository, WarehouseRepository warehouseRepository) {
        this.modelMapper = modelMapper;
        this.warehouseEventsWindowEntityRepository = warehouseEventsWindowEntityRepository;
        this.warehouseEventEntityRepository = warehouseEventEntityRepository;
        this.warehouseRepository = warehouseRepository;
    }


    @Override
    @Transactional
    public Warehouse fetchWarehouseWithEvents(UUID warehouseId) {
        return modelMapper.map(warehouseRepository.fetchWarehouseWithEvents(warehouseId), Warehouse.class);

    }

    @Override
    public Warehouse findBySellerId(SellerId sellerId) {
        return modelMapper.map(warehouseRepository.findBySellerId(sellerId), Warehouse.class);

    }

    @Override
    public Warehouse findBySellerIdAndWarehouseId(SellerId sellerId, UUID warehouseId) {
        return modelMapper.map(warehouseRepository.findBySellerIdAndWarehouseId(sellerId, warehouseId), Warehouse.class);
    }

    @Override
    @Transactional
    public Warehouse findBySellerIdAndMaterialType(SellerId sellerId, MaterialType materialType) {
        UUID sellerUuid = sellerId.sellerID();

        WarehouseEntity warehouseEntity = warehouseRepository.findBySellerIdAndMaterialType(sellerUuid, materialType);

        if (warehouseEntity == null) {
            return null;
        }

        WarehouseId warehouseId = new WarehouseId(warehouseEntity.getWarehouseId());
        SellerId domainSellerId = new SellerId(warehouseEntity.getSellerId());
        MaterialType domainMaterialType = MaterialType.valueOf(warehouseEntity.getMaterialType().toString());

        Warehouse warehouse = new Warehouse(warehouseId, domainSellerId, domainMaterialType);

        // Map WarehouseEventsWindowEntity to WarehouseEventsWindow
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
                            eventEntity.getMaterialType()
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
    }


    @Override
    @Transactional
    public void save(Warehouse warehouse , WarehouseEvent ignore) {
        WarehouseEntity warehouseEntity = new WarehouseEntity();

        warehouseEntity.setWarehouseId(warehouse.getWarehouseNumber().getId());
        warehouseEntity.setMaterialType(warehouse.getMaterialType());
        warehouseEntity.setSellerId(warehouse.getSellerId().sellerID());


        WarehouseEventsWindowEntity eventsWindowEntity = new WarehouseEventsWindowEntity();
        eventsWindowEntity.setWarehouseEventsWindowId(warehouse.getEventsWindow().getWarehouseEventsWindowId());
        eventsWindowEntity.setWarehouseId(warehouseEntity.getWarehouseId());

        // Map the list of WarehouseEventEntity
        List<WarehouseEventEntity> eventEntities = warehouse.getEventsWindow().getWarehouseEventList().stream()
                .map(event -> new WarehouseEventEntity(
                        event.id().getId(),
                        event.time(),
                        event.type().toString(),
                        event.materialTrueWeight(),
                        event.weighBridgeTicketId(),
                        eventsWindowEntity,
                        event.getMaterialType()
                ))
                .collect(Collectors.toList());

        eventsWindowEntity.setWarehouseEventList(eventEntities);

        warehouseEntity.setWarehouseEventsWindow(eventsWindowEntity);

        warehouseRepository.save(warehouseEntity);
    }




}

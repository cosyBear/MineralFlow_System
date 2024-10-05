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
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
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

    public WarehouseDataBaseAdapter(ModelMapper modelMapper, WarehouseEventsWindowEntityRepository warehouseEventsWindowEntityRepository, WarehouseEventEntityRepository warehouseEventEntityRepository, WarehouseRepository warehouseRepository) {
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
    public Warehouse findBySellerIdAndMaterialType(SellerId sellerId, MaterialType materialType) {
        UUID sellerUuid = sellerId.sellerID();

        // Fetch the WarehouseEntity from the repository
        WarehouseEntity warehouseEntity = warehouseRepository.findBySellerIdAndMaterialType(sellerUuid, materialType.toString());

        if (warehouseEntity == null) {
            return null; // Handle the case when no entity is found
        }

        // Map WarehouseEntity to Warehouse domain object
        WarehouseId warehouseId = new WarehouseId(warehouseEntity.getWarehouseId());
        SellerId domainSellerId = new SellerId(warehouseEntity.getSellerId());
        MaterialType domainMaterialType = MaterialType.valueOf(warehouseEntity.getMaterialType());

        // Instantiate the domain Warehouse object
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
                            eventsWindowEntity.getWarehouseEventsWindowId()))
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
    public void save(Warehouse warehouse) {
        // Create a new WarehouseEntity object
        WarehouseEntity warehouseEntity = new WarehouseEntity();

        // Set the fields from the domain model to the entity
        warehouseEntity.setWarehouseId(warehouse.getWarehouseNumber().getId());  // WarehouseId is mapped to a UUID
        warehouseEntity.setMaterialType(warehouse.getMaterialType().toString());  // Convert MaterialType enum to String
        warehouseEntity.setSellerId(warehouse.getSellerId().sellerID());  // Convert SellerId to UUID


        // Map the events window from the domain model
        WarehouseEventsWindowEntity eventsWindowEntity = new WarehouseEventsWindowEntity();
        eventsWindowEntity.setWarehouseEventsWindowId(warehouse.getEventsWindow().getWarehouseEventsWindowId());  // Set the ID of the events window
        eventsWindowEntity.setWarehouseId(warehouseEntity.getWarehouseId());  // Set the warehouse ID for the events window
        // Map the list of WarehouseEventEntity
        List<WarehouseEventEntity> eventEntities = warehouse.getEventsWindow().getWarehouseEventList().stream()
                .map(event -> new WarehouseEventEntity(
                        event.id().getId(),  // Map the event ID
                        event.time(),  // Map the timestamp
                        event.type().toString(),  // Convert event type to string
                        event.materialTrueWeight(),  // Map the material weighInTime
                        event.weighBridgeTicketId()  // Map the weighbridge ticket ID
                ))
                .collect(Collectors.toList());

        // Set the event list in the events window entity
        eventsWindowEntity.setWarehouseEventList(eventEntities);

        // Set the events window in the warehouse entity
        warehouseEntity.setWarehouseEventsWindow(eventsWindowEntity);

        // Save the warehouseEntity and its nested entities
        warehouseRepository.save(warehouseEntity);
    }




}

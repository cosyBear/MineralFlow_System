package be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence;

import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEventEntity;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence.Repository.WarehouseEventEntityRepository;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseEvent;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseEventId;
import be.kdg.prog6.warehouseBoundedContext.port.out.WarehouseEvent.WarehouseEventLoadPort;
import be.kdg.prog6.warehouseBoundedContext.port.out.WarehouseEvent.WarehouseEventSavePort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class WarehouseEventDataBaseAdapter implements WarehouseEventSavePort, WarehouseEventLoadPort {

    private final WarehouseEventEntityRepository warehouseEventEntityRepository;

    public WarehouseEventDataBaseAdapter(WarehouseEventEntityRepository warehouseEventEntityRepository) {
        this.warehouseEventEntityRepository = warehouseEventEntityRepository;
    }

    @Override
    public WarehouseEvent save(WarehouseEvent warehouseEvent) {
        WarehouseEventEntity eventEntity = mapToEntity(warehouseEvent);
        eventEntity = warehouseEventEntityRepository.save(eventEntity);
        return mapToDomain(eventEntity);
    }

    @Override
    public Optional<WarehouseEvent> findByWarehouseEventId(WarehouseEventId id) {
        return warehouseEventEntityRepository.findByWareHouseEventId(id.id())
                .map(this::mapToDomain);
    }

    @Override
    public Optional<WarehouseEvent> findByWeighBridgeTicketId(UUID weighBridgeTicketId) {
        return warehouseEventEntityRepository.findByWeighBridgeTicketId(weighBridgeTicketId)
                .map(this::mapToDomain);
    }

    @Override
    public WarehouseEvent updateEvent(WarehouseEventId id, double newAmount) {
        WarehouseEventEntity eventEntity = warehouseEventEntityRepository.findByWareHouseEventId(id.id())
                .orElseThrow(() -> new RuntimeException("Event not found"));
        eventEntity.setAmount(newAmount);
        warehouseEventEntityRepository.save(eventEntity);
        return mapToDomain(eventEntity);
    }

    // Custom mapping methods

    private WarehouseEvent mapToDomain(WarehouseEventEntity entity) {
        return new WarehouseEvent(
                new WarehouseEventId(entity.getWareHouseEventId()),
                entity.getTime(),
                entity.getType(),
                entity.getAmount(),
                entity.getWeighBridgeTicketId()
        );
    }

    private WarehouseEventEntity mapToEntity(WarehouseEvent event) {
        WarehouseEventEntity entity = new WarehouseEventEntity();
        entity.setWareHouseEventId(event.id().id());
        entity.setTime(event.time());
        entity.setType(event.type());
        entity.setAmount(event.materialTrueWeight());
        entity.setWeighBridgeTicketId(event.WeighBridgeTicketId());
        // Note: warehouseEventsWindow is managed elsewhere
        return entity;
    }
}

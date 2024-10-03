package be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence;


import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEventEntity;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence.Repository.WarehouseEventEntityRepository;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseEvent;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseEventId;
import be.kdg.prog6.warehouseBoundedContext.port.out.WarehouseEvent.WarehouseEventLoadPort;
import be.kdg.prog6.warehouseBoundedContext.port.out.WarehouseEvent.WarehouseEventSavePort;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
@Component
public class WarehouseEventDataBaseAdapter implements WarehouseEventSavePort , WarehouseEventLoadPort {

    private final WarehouseEventEntityRepository warehouseEventEntityRepository;
    private final ModelMapper modelMapper;

    public WarehouseEventDataBaseAdapter(WarehouseEventEntityRepository warehouseEventEntityRepository, ModelMapper modelMapper) {
        this.warehouseEventEntityRepository = warehouseEventEntityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public WarehouseEvent save(WarehouseEvent warehouseEvent) {
        var eventEntity = modelMapper.map(warehouseEvent, WarehouseEventEntity.class);
        eventEntity = warehouseEventEntityRepository.save(eventEntity);
        return modelMapper.map(eventEntity, WarehouseEvent.class);
    }

    @Override
    public Optional<WarehouseEvent> findByWarehouseEventId(WarehouseEventId id) {
        return warehouseEventEntityRepository.findByWareHouseEventId(id)
                .map(entity -> modelMapper.map(entity, WarehouseEvent.class));
    }

    @Override
    public Optional<WarehouseEvent> findByWeighBridgeTicketId(UUID weighBridgeTicketId) {
        return warehouseEventEntityRepository.findByWeighBridgeTicketId(weighBridgeTicketId)
                .map(entity -> modelMapper.map(entity, WarehouseEvent.class));
    }

    @Override
    public WarehouseEvent updateEvent(WarehouseEventId id, double newAmount) {
        var eventEntity = warehouseEventEntityRepository.findByWareHouseEventId(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        eventEntity.setAmount(newAmount);
        warehouseEventEntityRepository.save(eventEntity);
        return modelMapper.map(eventEntity, WarehouseEvent.class);
    }
}

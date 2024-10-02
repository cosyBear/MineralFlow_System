package be.kdg.prog6.boundedcontextB.adapters.out.persistence.Adapters;


import be.kdg.prog6.boundedcontextB.adapters.out.JPAEntity.WarehouseEventEntity;
import be.kdg.prog6.boundedcontextB.adapters.out.persistence.Repository.WarehouseEventEntityRepository;
import be.kdg.prog6.boundedcontextB.domain.WarehouseEvent;
import be.kdg.prog6.boundedcontextB.domain.WarehouseEventId;
import be.kdg.prog6.boundedcontextB.port.out.WarehouseEvent.WarehouseEventLoadPort;
import be.kdg.prog6.boundedcontextB.port.out.WarehouseEvent.WarehouseEventSavePort;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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

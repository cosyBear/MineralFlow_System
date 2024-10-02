package be.kdg.prog6.boundedcontextB.adapters.out.persistence.Adapters;

import be.kdg.prog6.boundedcontextB.adapters.out.JPAEntity.WarehouseEventsWindowEntity;
import be.kdg.prog6.boundedcontextB.adapters.out.persistence.Repository.WarehouseEventEntityRepository;
import be.kdg.prog6.boundedcontextB.adapters.out.persistence.Repository.WarehouseEventsWindowEntityRepository;
import be.kdg.prog6.boundedcontextB.domain.WarehouseEvent;
import be.kdg.prog6.boundedcontextB.domain.WarehouseEventId;
import be.kdg.prog6.boundedcontextB.domain.WarehouseEventsWindow;
import be.kdg.prog6.boundedcontextB.domain.WarehouseId;
import be.kdg.prog6.boundedcontextB.port.out.WarehouseEvent.WarehouseEventSavePort;
import be.kdg.prog6.boundedcontextB.port.out.WarehouseEventsWindow.WarehouseEventsWindowLoadPort;
import org.modelmapper.ModelMapper;

import java.util.Optional;
import java.util.UUID;


import be.kdg.prog6.boundedcontextB.adapters.out.JPAEntity.WarehouseEventEntity;
import be.kdg.prog6.boundedcontextB.adapters.out.JPAEntity.WarehouseEventsWindowEntity;
import be.kdg.prog6.boundedcontextB.domain.WarehouseEvent;
import be.kdg.prog6.boundedcontextB.domain.WarehouseEventId;
import be.kdg.prog6.boundedcontextB.domain.WarehouseEventsWindow;
import be.kdg.prog6.boundedcontextB.domain.WarehouseId;
import be.kdg.prog6.boundedcontextB.port.out.WarehouseEvent.WarehouseEventSavePort;
import be.kdg.prog6.boundedcontextB.port.out.WarehouseEventsWindow.WarehouseEventsWindowLoadPort;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class WarehouseEventsWindowDataBaseAdapter implements WarehouseEventsWindowLoadPort, WarehouseEventSavePort {

    private final WarehouseEventsWindowEntityRepository windowRepository;
    private  final WarehouseEventEntityRepository eventRepository;
    private final ModelMapper modelMapper;

    public WarehouseEventsWindowDataBaseAdapter(WarehouseEventsWindowEntityRepository windowRepository, ModelMapper modelMapper , WarehouseEventEntityRepository eventRepository) {
        this.windowRepository = windowRepository;
        this.modelMapper = modelMapper;
        this.eventRepository = eventRepository;
    }

    @Override
    public WarehouseEvent save(WarehouseEvent warehouseEvent) {
        WarehouseEventEntity eventEntity = modelMapper.map(warehouseEvent, WarehouseEventEntity.class);
        eventEntity = eventRepository.save(eventEntity);
        return modelMapper.map(eventEntity, WarehouseEvent.class);
    }

    @Override
    public WarehouseEvent updateEvent(WarehouseEventId id, double newAmount) {
        Optional<WarehouseEventEntity> optionalEventEntity = eventRepository.findById(id.id());
        if (optionalEventEntity.isPresent()) {
            WarehouseEventEntity eventEntity = optionalEventEntity.get();
            eventEntity.setAmount(newAmount);
            eventEntity = eventRepository.save(eventEntity);
            return modelMapper.map(eventEntity, WarehouseEvent.class);
        }
        throw new RuntimeException("WarehouseEvent not found with ID: " + id);
    }

    @Override
    public Optional<WarehouseEventsWindow> findByWarehouseId(WarehouseId warehouseId) {
        Optional<WarehouseEventsWindowEntity> optionalEntity = windowRepository.findByWarehouseId(warehouseId);
        return optionalEntity.map(entity -> modelMapper.map(entity, WarehouseEventsWindow.class));
    }

    @Override
    public Optional<WarehouseEventsWindowEntity> findWarehouseEventsWindowEntitiesByWarehouseEventWindowId(UUID warehouseEventWindowId) {
        return windowRepository.findWarehouseEventsWindowEntitiesByWarehouseEventWindowId(warehouseEventWindowId);
    }


}


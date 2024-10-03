package be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence;

import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEventsWindowEntity;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence.Repository.WarehouseEventEntityRepository;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence.Repository.WarehouseEventsWindowEntityRepository;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseEventsWindow;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseId;
import be.kdg.prog6.warehouseBoundedContext.port.out.WarehouseEventsWindow.WarehouseEventsWindowLoadPort;
import be.kdg.prog6.warehouseBoundedContext.port.out.WarehouseEventsWindow.WarehouseEventsWindowSavePort;
import org.modelmapper.ModelMapper;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class WarehouseEventsWindowDataBaseAdapter implements WarehouseEventsWindowLoadPort, WarehouseEventsWindowSavePort {

    private final WarehouseEventsWindowEntityRepository windowRepository;
    private final WarehouseEventEntityRepository eventRepository;
    private final ModelMapper modelMapper;

    public WarehouseEventsWindowDataBaseAdapter(WarehouseEventsWindowEntityRepository windowRepository, ModelMapper modelMapper, WarehouseEventEntityRepository eventRepository) {
        this.windowRepository = windowRepository;
        this.modelMapper = modelMapper;
        this.eventRepository = eventRepository;
    }

    public void save(WarehouseEventsWindow warehouseEventsWindow) {
        WarehouseEventsWindowEntity windowEntity = modelMapper.map(warehouseEventsWindow, WarehouseEventsWindowEntity.class);

        windowRepository.save(windowEntity);
    }


    @Override
    public Optional<WarehouseEventsWindow> findByWarehouseId(WarehouseId warehouseId) {
        Optional<WarehouseEventsWindowEntity> optionalEntity = windowRepository.findByWarehouseId(warehouseId.id());
        return optionalEntity.map(entity -> modelMapper.map(entity, WarehouseEventsWindow.class));
    }

    @Override
    public Optional<WarehouseEventsWindow> findWarehouseEventsWindowById(UUID warehouseEventWindowId) {

        return Optional.of(modelMapper.map(windowRepository.findByWarehouseEventWindowId(warehouseEventWindowId), WarehouseEventsWindow.class));

    }


}


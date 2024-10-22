package be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence;

import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEventsWindowEntity;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEventEntity;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence.Repository.WarehouseEventEntityRepository;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence.Repository.WarehouseEventsWindowEntityRepository;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseEventId;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseEventsWindow;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseEvent;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseId;
import be.kdg.prog6.warehouseBoundedContext.port.out.WarehouseEventsWindow.WarehouseEventsWindowLoadPort;
import be.kdg.prog6.warehouseBoundedContext.port.out.WarehouseEventsWindow.WarehouseEventsWindowSavePort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;
import org.modelmapper.ModelMapper;


@Service
public class WarehouseEventsWindowDataBaseAdapter implements WarehouseEventsWindowLoadPort, WarehouseEventsWindowSavePort {

    private final WarehouseEventsWindowEntityRepository windowRepository;
    private final WarehouseEventEntityRepository eventRepository;
    private final ModelMapper modelMapper;

    public WarehouseEventsWindowDataBaseAdapter(WarehouseEventsWindowEntityRepository windowRepository,
                                                WarehouseEventEntityRepository eventRepository,
                                                @Qualifier("warehouse") ModelMapper modelMapper) {
        this.windowRepository = windowRepository;
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Optional<WarehouseEventsWindowEntity> fetchByWarehouseEventsWindowIdWithEvents(UUID warehouseEventsWindowId) {
        return windowRepository.fetchByWarehouseEventsWindowIdWithEvents(warehouseEventsWindowId);
    }

    @Override
    public Optional<WarehouseEventsWindowEntity> fetchByWarehouseIdWithEvents(UUID warehouseId) {
        return windowRepository.fetchByWarehouseIdWithEvents(warehouseId);
    }

    @Override
    public void save(WarehouseEventsWindow warehouseEventsWindow) {
        WarehouseEventsWindowEntity windowEntity = modelMapper.map(warehouseEventsWindow, WarehouseEventsWindowEntity.class);
        windowRepository.save(windowEntity);

        for (WarehouseEvent event : warehouseEventsWindow.getWarehouseEventList()) {
            WarehouseEventEntity eventEntity = modelMapper.map(event, WarehouseEventEntity.class);
            eventRepository.save(eventEntity);
        }
    }
}

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
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WarehouseEventsWindowDataBaseAdapter implements WarehouseEventsWindowLoadPort, WarehouseEventsWindowSavePort {

    private final WarehouseEventsWindowEntityRepository windowRepository;
    private final WarehouseEventEntityRepository eventRepository;

    public WarehouseEventsWindowDataBaseAdapter(WarehouseEventsWindowEntityRepository windowRepository,
                                                WarehouseEventEntityRepository eventRepository) {
        this.windowRepository = windowRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public void save(WarehouseEventsWindow warehouseEventsWindow) {
        WarehouseEventsWindowEntity windowEntity = mapToEntity(warehouseEventsWindow);
        windowRepository.save(windowEntity);
    }

    @Override
    public Optional<WarehouseEventsWindow> findByWarehouseId(WarehouseId warehouseId) {
        Optional<WarehouseEventsWindowEntity> optionalEntity = windowRepository.findByWarehouseId(warehouseId.id());
        return optionalEntity.map(this::mapToDomain);
    }

    @Override
    public Optional<WarehouseEventsWindow> findWarehouseEventsWindowById(UUID warehouseEventWindowId) {
        Optional<WarehouseEventsWindowEntity> optionalEntity = windowRepository.findByWarehouseEventWindowId(warehouseEventWindowId);
        return optionalEntity.map(this::mapToDomain);
    }

    // Custom mapping methods

    private WarehouseEventsWindow mapToDomain(WarehouseEventsWindowEntity entity) {
        WarehouseEventsWindow window = new WarehouseEventsWindow();
        window.setWarehouseEventsWindowId(entity.getWarehouseEventWindowId());
        window.setWarehouseId(new WarehouseId(entity.getWarehouseId()));

        if (entity.getWarehouseEventList() != null) {
            window.setWarehouseEventList(entity.getWarehouseEventList().stream()
                    .map(this::mapEventToDomain)
                    .collect(Collectors.toList()));
        }

        return window;
    }

    private WarehouseEventsWindowEntity mapToEntity(WarehouseEventsWindow window) {
        WarehouseEventsWindowEntity entity = new WarehouseEventsWindowEntity();
        entity.setWarehouseEventWindowId(window.getWarehouseEventsWindowId());
        entity.setWarehouseId(window.getWarehouseId().id());

        if (window.getWarehouseEventList() != null) {
            entity.setWarehouseEventList(window.getWarehouseEventList().stream()
                    .map(this::mapEventToEntity)
                    .collect(Collectors.toList()));

            // Set the back-reference
            entity.getWarehouseEventList().forEach(eventEntity -> eventEntity.setWarehouseEventsWindow(entity));
        }

        return entity;
    }

    private WarehouseEvent mapEventToDomain(WarehouseEventEntity entity) {
        return new WarehouseEvent(
                new WarehouseEventId(entity.getWareHouseEventId()),
                entity.getTime(),
                entity.getType(),
                entity.getAmount(),
                entity.getWeighBridgeTicketId()
        );
    }

    private WarehouseEventEntity mapEventToEntity(WarehouseEvent event) {
        WarehouseEventEntity entity = new WarehouseEventEntity();
        entity.setWareHouseEventId(event.id().id());
        entity.setTime(event.time());
        entity.setType(event.type());
        entity.setAmount(event.materialTrueWeight());
        entity.setWeighBridgeTicketId(event.WeighBridgeTicketId());
        // The warehouseEventsWindow is set in mapToEntity method
        return entity;
    }
}

package be.kdg.prog6.boundedcontextB.port.out.WarehouseEventsWindow;

import be.kdg.prog6.boundedcontextB.adapters.out.JPAEntity.WarehouseEventsWindowEntity;
import be.kdg.prog6.boundedcontextB.domain.WarehouseEventsWindow;
import be.kdg.prog6.boundedcontextB.domain.WarehouseId;

import java.util.Optional;
import java.util.UUID;

public interface WarehouseEventsWindowLoadPort {

    Optional<WarehouseEventsWindow> findByWarehouseId(WarehouseId warehouseId);
    Optional<WarehouseEventsWindowEntity> findWarehouseEventsWindowEntitiesByWarehouseEventWindowId(UUID warehouseEventWindowId);

}

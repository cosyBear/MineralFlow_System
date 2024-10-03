package be.kdg.prog6.warehouseBoundedContext.port.out.WarehouseEventsWindow;

import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseEventsWindow;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseId;

import java.util.Optional;
import java.util.UUID;

public interface WarehouseEventsWindowLoadPort {

    Optional<WarehouseEventsWindow> findByWarehouseId(WarehouseId warehouseId);
    Optional<WarehouseEventsWindow> findWarehouseEventsWindowById(UUID warehouseEventWindowId);

}

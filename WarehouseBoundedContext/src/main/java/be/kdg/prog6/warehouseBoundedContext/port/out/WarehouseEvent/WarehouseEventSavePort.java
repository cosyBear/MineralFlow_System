package be.kdg.prog6.warehouseBoundedContext.port.out.WarehouseEvent;

import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseEvent;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseEventId;

public interface WarehouseEventSavePort {

    void save(WarehouseEvent warehouseEvent);


}

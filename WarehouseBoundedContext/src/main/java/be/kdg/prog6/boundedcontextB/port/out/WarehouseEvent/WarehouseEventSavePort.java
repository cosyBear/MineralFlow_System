package be.kdg.prog6.boundedcontextB.port.out.WarehouseEvent;

import be.kdg.prog6.boundedcontextB.domain.WarehouseEvent;
import be.kdg.prog6.boundedcontextB.domain.WarehouseEventId;

public interface WarehouseEventSavePort {

    WarehouseEvent save(WarehouseEvent warehouseEvent);


    WarehouseEvent updateEvent(WarehouseEventId id, double newAmount);

}

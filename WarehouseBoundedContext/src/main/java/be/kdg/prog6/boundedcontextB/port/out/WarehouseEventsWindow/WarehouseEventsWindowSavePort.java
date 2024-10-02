package be.kdg.prog6.boundedcontextB.port.out.WarehouseEventsWindow;

import be.kdg.prog6.boundedcontextB.domain.WarehouseEventsWindow;

public interface WarehouseEventsWindowSavePort {

    WarehouseEventsWindow save(WarehouseEventsWindow warehouseEventsWindow);
    WarehouseEventsWindow updateWindow(WarehouseEventsWindow window);

}

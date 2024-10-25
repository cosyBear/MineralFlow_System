package be.kdg.prog6.warehouseBoundedContext.port.out.Warehouse;

import be.kdg.prog6.warehouseBoundedContext.domain.Warehouse;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseEvent;

import java.util.List;

public interface WarehouseSavePort {
    void save(Warehouse warehouse, WarehouseEvent event);

    default void saveList(Warehouse warehouse, List<WarehouseEvent> events) {
        for (WarehouseEvent event : events) {
            save(warehouse, event);
        }
    }
}

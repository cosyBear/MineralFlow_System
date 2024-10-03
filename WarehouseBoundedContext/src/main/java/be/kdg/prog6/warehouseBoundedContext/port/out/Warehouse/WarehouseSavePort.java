package be.kdg.prog6.warehouseBoundedContext.port.out.Warehouse;

import be.kdg.prog6.warehouseBoundedContext.domain.Warehouse;

public interface WarehouseSavePort {
    Warehouse save(Warehouse warehouse);
    Warehouse update(Warehouse warehouse);

}

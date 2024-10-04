package be.kdg.prog6.warehouseBoundedContext.port.out.Warehouse;

import be.kdg.prog6.warehouseBoundedContext.domain.Warehouse;

public interface WarehouseSavePort {
    void save(Warehouse warehouse);

}

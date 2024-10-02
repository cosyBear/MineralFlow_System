package be.kdg.prog6.boundedcontextB.port.out.Warehouse;

import be.kdg.prog6.boundedcontextB.domain.Warehouse;

public interface WarehouseSavePort {
    Warehouse save(Warehouse warehouse);
    Warehouse update(Warehouse warehouse);

}

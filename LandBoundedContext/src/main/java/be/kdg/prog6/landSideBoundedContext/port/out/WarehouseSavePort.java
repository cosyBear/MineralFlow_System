package be.kdg.prog6.landSideBoundedContext.port.out;

import be.kdg.prog6.landSideBoundedContext.domain.Warehouse;
@FunctionalInterface

public interface WarehouseSavePort {

    void Save(Warehouse warehouse );
}

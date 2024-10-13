package be.kdg.prog6.landSideBoundedContext.port.out;

import be.kdg.prog6.landSideBoundedContext.domain.WareHouse;
@FunctionalInterface

public interface WarehouseSavePort {




    void Save(WareHouse warehouse );
}

package be.kdg.prog6.boundedcontextB.port.out.Warehouse;


import be.kdg.prog6.boundedcontextB.domain.Warehouse;
import be.kdg.prog6.boundedcontextB.domain.WarehouseId;
import be.kdg.prog6.boundedcontextB.domain.SellerId;

import java.util.Optional;

public interface WarehouseLoadPort {



    // Find WarehouseEntity by SellerId
    Optional<Warehouse> findBySellerId(SellerId sellerId);

    // Find WarehouseEntity by WarehouseId
    Optional<Warehouse> findByWarehouseNumber(WarehouseId warehouseId);

}

package be.kdg.prog6.warehouseBoundedContext.port.out.Warehouse;


import be.kdg.prog6.warehouseBoundedContext.domain.MaterialType;
import be.kdg.prog6.warehouseBoundedContext.domain.Warehouse;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseId;
import be.kdg.prog6.warehouseBoundedContext.domain.SellerId;

import java.util.Optional;

public interface WarehouseLoadPort {



    // Find WarehouseEntity by SellerId
    Optional<Warehouse> findBySellerId(SellerId sellerId);

    // Find WarehouseEntity by WarehouseId
    Optional<Warehouse> findByWarehouseNumber(WarehouseId warehouseId);

    Warehouse findBySellerIdAndMaterialType(SellerId sellerId , MaterialType materialType);

}

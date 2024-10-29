package be.kdg.prog6.warehouseBoundedContext.port.out;


import be.kdg.prog6.warehouseBoundedContext.domain.Warehouse;
import be.kdg.prog6.warehouseBoundedContext.domain.SellerId;
import domain.MaterialType;

import java.util.List;
import java.util.UUID;

public interface WarehouseLoadPort {



    Warehouse fetchWarehouseWithEvents(UUID warehouseId);


    Warehouse findBySellerId(SellerId sellerId);


    Warehouse findBySellerIdAndWarehouseId(
           SellerId sellerId,
          UUID warehouseId);

    Warehouse findBySellerIdAndMaterialType(
            SellerId sellerId,
            MaterialType materialType);


    List<Warehouse> loadAllWarehouses();
}

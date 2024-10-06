package be.kdg.prog6.warehouseBoundedContext.port.out.Warehouse;


import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEntity;
import be.kdg.prog6.warehouseBoundedContext.domain.MaterialType;
import be.kdg.prog6.warehouseBoundedContext.domain.Warehouse;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseId;
import be.kdg.prog6.warehouseBoundedContext.domain.SellerId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
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
}

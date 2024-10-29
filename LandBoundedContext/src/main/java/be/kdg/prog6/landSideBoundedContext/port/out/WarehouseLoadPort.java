package be.kdg.prog6.landSideBoundedContext.port.out;

import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import be.kdg.prog6.landSideBoundedContext.domain.Warehouse;
import domain.MaterialType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WarehouseLoadPort {



    Warehouse findBySellerIdAAndMaterialType(SellerId sellerId , MaterialType materialType);


    List<Warehouse> warehouseOverview();
}

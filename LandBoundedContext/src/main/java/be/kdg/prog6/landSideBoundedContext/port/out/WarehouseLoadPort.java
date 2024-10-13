package be.kdg.prog6.landSideBoundedContext.port.out;

import be.kdg.prog6.landSideBoundedContext.domain.WareHouse;
import domain.MaterialType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WarehouseLoadPort {

    List<WareHouse> findAllBySellerId(UUID sellerId);

    WareHouse findBySellerId(UUID sellerId);

    WareHouse findBySellerIdAAndMaterialType(UUID sellerId , MaterialType materialType);

    Optional<WareHouse> findById(UUID id);

    List<WareHouse> warehouseOverview();
}

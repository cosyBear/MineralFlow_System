package be.kdg.prog6.landSideBoundedContext.domain;

import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;

import java.util.UUID;

public record WarehouseOverviewQuery(UUID warehouseId, UUID sellerId, double totalRawMaterial,
                                     MaterialType materialType , boolean isWarehouseFull, boolean isWarehouseOverloaded) {


}

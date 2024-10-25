package be.kdg.prog6.landSideBoundedContext.port.out;

import domain.MaterialType;

import java.util.UUID;

public record WarehouseOverviewQuery(UUID warehouseId, UUID sellerId, double totalRawMaterial,
                                     MaterialType materialType , boolean isWarehouseFull, boolean isWarehouseOverloaded) {


}

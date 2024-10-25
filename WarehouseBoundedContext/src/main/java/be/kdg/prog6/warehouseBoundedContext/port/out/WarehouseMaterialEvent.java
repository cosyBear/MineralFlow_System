package be.kdg.prog6.warehouseBoundedContext.port.out;

import domain.MaterialType;

import java.util.UUID;

public record WarehouseMaterialEvent(UUID warehouseId, double materialAmountInWarehouse , MaterialType materialType, UUID sellerId ) {
}

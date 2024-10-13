package be.kdg.prog6.warehouseBoundedContext.domain;

import domain.MaterialType;

import java.util.UUID;

public record WarehouseMaterialEvent(UUID warehouseId, double materialAmountInWarehouse , MaterialType materialType, UUID sellerId) {
}

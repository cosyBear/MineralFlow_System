package be.kdg.prog6.landSideBoundedContext.domain;

import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import be.kdg.prog6.landSideBoundedContext.domain.Id.WarehouseId;

public record UpdateWarehouseCommand(WarehouseId warehouseId, double materialAmountInWarehouse , MaterialType materialType, SellerId sellerId) {
}
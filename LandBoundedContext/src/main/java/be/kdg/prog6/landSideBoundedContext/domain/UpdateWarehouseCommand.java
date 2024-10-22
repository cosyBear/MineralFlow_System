package be.kdg.prog6.landSideBoundedContext.domain;

import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import be.kdg.prog6.landSideBoundedContext.domain.Id.WarehouseId;
import domain.MaterialType;

public record UpdateWarehouseCommand(WarehouseId warehouseId, double amount, MaterialType materialType, SellerId sellerId ) {
}
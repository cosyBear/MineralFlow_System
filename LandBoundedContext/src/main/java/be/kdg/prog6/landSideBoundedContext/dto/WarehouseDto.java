package be.kdg.prog6.landSideBoundedContext.dto;

import be.kdg.prog6.landSideBoundedContext.domain.MaterialType;

import java.util.UUID;


public record WarehouseDto(String  warehouseId, double materialAmountInWarehouse , String  materialType, String  sellerId) {
}
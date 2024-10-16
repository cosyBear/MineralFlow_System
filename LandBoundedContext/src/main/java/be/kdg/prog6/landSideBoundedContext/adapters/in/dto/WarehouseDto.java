package be.kdg.prog6.landSideBoundedContext.adapters.in.dto;


import java.util.UUID;

public record WarehouseDto(UUID warehouseId, double materialAmountInWarehouse , String  materialType, UUID  sellerId) {
}
package be.kdg.prog6.landSideBoundedContext.adapters.in.dto;


import be.kdg.prog6.landSideBoundedContext.domain.WarehouseAction;

import java.util.UUID;

public record WarehouseDto(UUID warehouseId, double materialAmountInWarehouse , String  materialType, UUID  sellerId , WarehouseAction action) {
}
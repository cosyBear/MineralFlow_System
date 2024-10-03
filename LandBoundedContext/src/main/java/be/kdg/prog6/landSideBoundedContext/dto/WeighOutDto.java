package be.kdg.prog6.landSideBoundedContext.dto;

import be.kdg.prog6.landSideBoundedContext.domain.LicensePlate;
import be.kdg.prog6.landSideBoundedContext.domain.MaterialType;
import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;

import java.time.LocalDateTime;

public record WeighOutDto (LicensePlate licensePlate, double endWeight, MaterialType materialType, SellerId sellerId,
                           LocalDateTime weighInTime , String warehouseStatus) {

}

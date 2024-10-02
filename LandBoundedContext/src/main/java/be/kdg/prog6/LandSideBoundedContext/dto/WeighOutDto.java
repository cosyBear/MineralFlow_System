package be.kdg.prog6.LandSideBoundedContext.dto;

import be.kdg.prog6.LandSideBoundedContext.domain.LicensePlate;
import be.kdg.prog6.LandSideBoundedContext.domain.MaterialType;
import be.kdg.prog6.LandSideBoundedContext.domain.SellerId;

import java.time.LocalDateTime;

public record WeighOutDto (LicensePlate licensePlate, double EndWeight, MaterialType materialType, SellerId sellerId,
                           LocalDateTime weighInTime) {

}

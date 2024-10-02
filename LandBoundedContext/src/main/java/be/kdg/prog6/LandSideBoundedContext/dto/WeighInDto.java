package be.kdg.prog6.LandSideBoundedContext.dto;

import be.kdg.prog6.LandSideBoundedContext.domain.LicensePlate;
import be.kdg.prog6.LandSideBoundedContext.domain.MaterialType;
import be.kdg.prog6.LandSideBoundedContext.domain.SellerId;

import java.time.LocalDateTime;

/**
 * @param grossWeight Truck + material
 */
public record WeighInDto(LicensePlate licensePlate, double startWeight, MaterialType materialType, SellerId sellerId,
                         LocalDateTime weighInTime) {

}

package be.kdg.prog6.LandSideBoundedContext.port.in;

import be.kdg.prog6.LandSideBoundedContext.domain.LicensePlate;
import be.kdg.prog6.LandSideBoundedContext.domain.MaterialType;
import be.kdg.prog6.LandSideBoundedContext.domain.SellerId;

import java.time.LocalDateTime;

public record  weighTruckInCommand (LicensePlate licensePlate, double startWeight, MaterialType materialType, SellerId sellerId,
                         LocalDateTime weighInTime) {

}

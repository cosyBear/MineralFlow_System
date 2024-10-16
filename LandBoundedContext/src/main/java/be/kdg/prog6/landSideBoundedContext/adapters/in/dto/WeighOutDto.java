package be.kdg.prog6.landSideBoundedContext.adapters.in.dto;

import be.kdg.prog6.landSideBoundedContext.domain.LicensePlate;
import domain.MaterialType;

import java.time.LocalDateTime;

public record WeighOutDto (LicensePlate licensePlate, double endWeight, MaterialType materialType, String sellerId,
                           LocalDateTime weighInTime , String WeighBridgeTicketId) {

}

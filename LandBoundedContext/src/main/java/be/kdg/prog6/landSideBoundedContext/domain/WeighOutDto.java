package be.kdg.prog6.landSideBoundedContext.domain;

import java.time.LocalDateTime;

public record WeighOutDto (LicensePlate licensePlate, double endWeight, MaterialType materialType, String sellerId,
                           LocalDateTime weighInTime , String warehouseStatus, String WeighBridgeTicketId) {

}

package be.kdg.prog6.landSideBoundedContext.domain;

import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import be.kdg.prog6.landSideBoundedContext.domain.Id.WeighBridgeTicketId;

import java.time.LocalDateTime;

public record weighTruckOutCommand (LicensePlate licensePlate, double endWeight, MaterialType materialType, SellerId sellerId,
                                    LocalDateTime weighOutTime , String warehouseStatus , WeighBridgeTicketId WeighBridgeTicketId ) {

}

package be.kdg.prog6.landSideBoundedContext.domain;

import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import be.kdg.prog6.landSideBoundedContext.domain.Id.WeighBridgeTicketId;
import domain.MaterialType;

import java.time.LocalDateTime;

public record weighTruckOutCommand (LicensePlate licensePlate, double endWeight, MaterialType materialType, SellerId sellerId,
                                    LocalDateTime weighOutTime  , WeighBridgeTicketId WeighBridgeTicketId ) {

}

package be.kdg.prog6.boundedcontextB.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public record  weighTruckInCommand (UUID weighBridgeTicketId, String licensePlate , SellerId sellerId , double grossWeight, MaterialType materialType,
                                LocalDateTime weighInTime) {

}

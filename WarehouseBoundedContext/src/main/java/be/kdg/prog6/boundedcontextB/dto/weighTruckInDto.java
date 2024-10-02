package be.kdg.prog6.boundedcontextB.dto;

import be.kdg.prog6.boundedcontextB.domain.MaterialType;
import be.kdg.prog6.boundedcontextB.domain.SellerId;

import java.time.LocalDateTime;
import java.util.UUID;

public record  weighTruckInDto (UUID weighBridgeTicketId, String licensePlate ,  SellerId sellerId , double grossWeight,  MaterialType materialType,
                                LocalDateTime weighInTime) {

}

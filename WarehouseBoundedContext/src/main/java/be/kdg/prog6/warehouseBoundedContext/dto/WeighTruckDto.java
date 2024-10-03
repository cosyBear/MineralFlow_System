package be.kdg.prog6.warehouseBoundedContext.dto;

import java.time.LocalDateTime;


public record WeighTruckDto(
        String weighBridgeTicketId,
        String licensePlate,  // change to simple String
        String sellerId,      // change to simple String or UUID
        String materialType,
        LocalDateTime weighTime,
        double weight,
        String warehouseStatus
) {}

package be.kdg.prog6.warehouseBoundedContext.adapters.dto;

import java.time.LocalDateTime;
import java.util.UUID;


public record WeighInTruckDto(
        UUID weighBridgeTicketId,
        String licensePlate,
        UUID sellerId,
        String materialType,
        LocalDateTime weighInTime,
        double grossWeight
) {}

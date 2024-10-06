package be.kdg.prog6.warehouseBoundedContext.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record  WeighOutTruckDto        (UUID weighBridgeTicketId,
                                        String licensePlate,  // change to simple String
                                        UUID sellerId,      // change to simple String or UUID
                                        String materialType,
                                        LocalDateTime weighOutTime,
                                        double endWeight,
                                        String warehouseStatus
) {}

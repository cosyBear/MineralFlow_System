package be.kdg.prog6.watersideboundedcontext.adapters.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ShipmentCompletedDto(UUID purchaseOrderId ,
                                   UUID vesselNumber,
                                   LocalDateTime completionTime   ) {
}

package be.kdg.prog6.watersideboundedcontext.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public record ShipmentOrderEvent( UUID purchaseOrder,
         UUID vesselNumber,
         LocalDateTime arrivalTime,
         LocalDateTime departureTime) {
}

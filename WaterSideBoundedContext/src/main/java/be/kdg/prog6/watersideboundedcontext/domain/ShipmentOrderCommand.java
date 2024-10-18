package be.kdg.prog6.watersideboundedcontext.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public record ShipmentOrderCommand(UUID purchaseOrder, Vessel vesselNumber, LocalDateTime arrivalTime) {
}

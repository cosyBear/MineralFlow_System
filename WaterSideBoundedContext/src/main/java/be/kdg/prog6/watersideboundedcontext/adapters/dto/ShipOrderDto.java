package be.kdg.prog6.watersideboundedcontext.adapters.dto;

import java.time.LocalDateTime;
import java.util.UUID;


public record ShipOrderDto(UUID purchaseOrder, UUID vesselNumber,LocalDateTime arrivalTime) {
}

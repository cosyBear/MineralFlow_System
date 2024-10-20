package be.kdg.prog6.watersideboundedcontext.adapters.dto;

import java.time.LocalDateTime;
import java.util.UUID;


public record ShipOrderDto(String purchaseOrder, String vesselNumber,String arrivalTime) {
}

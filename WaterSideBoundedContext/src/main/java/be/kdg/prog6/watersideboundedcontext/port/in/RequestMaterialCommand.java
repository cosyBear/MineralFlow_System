package be.kdg.prog6.watersideboundedcontext.port.in;

import java.time.LocalDateTime;
import java.util.UUID;

public record RequestMaterialCommand(UUID purchaseOrder , UUID shipmentOrder , UUID vesselNumber, LocalDateTime arrivalTime) {
}

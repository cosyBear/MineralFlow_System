package be.kdg.prog6.watersideboundedcontext.port.in;

import java.time.LocalDateTime;
import java.util.UUID;

public record RequestMaterialEvent(UUID purchaseOrder,
                                   UUID vesselNumber,
                                   LocalDateTime arrivalTime) {
}

package be.kdg.prog6.watersideboundedcontext.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public record ShipmentCompletedCommand (UUID purchaseOrderId ,
                                       UUID vesselNumber,
                                       LocalDateTime departureTime   ){
}

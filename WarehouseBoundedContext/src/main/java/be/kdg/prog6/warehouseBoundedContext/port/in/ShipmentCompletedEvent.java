package be.kdg.prog6.warehouseBoundedContext.port.in;

import java.time.LocalDateTime;
import java.util.UUID;


public record ShipmentCompletedEvent(UUID purchaseOrderId ,
                                     UUID vesselNumber,
                                     LocalDateTime completionTime    ) {


}

package be.kdg.prog6.warehouseBoundedContext.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


public record ShipmentCompletedEvent(UUID purchaseOrderId ,
                                     UUID vesselNumber,
                                     LocalDateTime completionTime    ) {


}

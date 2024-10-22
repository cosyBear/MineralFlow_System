package be.kdg.prog6.warehouseBoundedContext.adapters.dto;
import java.time.LocalDateTime;
import java.util.UUID;


public record ShipmentDto( UUID purchaseOrder,
                           UUID vesselNumber,
                           LocalDateTime arrivalTime)  {
}

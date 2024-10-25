package be.kdg.prog6.landSideBoundedContext.port.out;

import java.time.LocalDateTime;
import java.util.UUID;

public record TruckOnTimeQuery(String  licensePlate , UUID sellerId , String materialType , LocalDateTime TimeOfArrival , boolean onTime) {
}

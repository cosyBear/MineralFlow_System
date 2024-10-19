package be.kdg.prog6.landSideBoundedContext.domain;

import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import domain.MaterialType;

import java.time.LocalDateTime;
import java.util.UUID;

public record TruckOnTimeQuery(String  licensePlate , UUID sellerId , String materialType , LocalDateTime TimeOfArrival , boolean onTime) {
}

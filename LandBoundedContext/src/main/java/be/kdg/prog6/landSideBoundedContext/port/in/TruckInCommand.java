package be.kdg.prog6.landSideBoundedContext.port.in;

import be.kdg.prog6.landSideBoundedContext.domain.LicensePlate;

import java.time.LocalDateTime;

public record TruckInCommand(LicensePlate licensePlate , double weight , LocalDateTime time )  {
}

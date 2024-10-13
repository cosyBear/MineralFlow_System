package be.kdg.prog6.landSideBoundedContext.domain;

import java.time.LocalDateTime;

public record TruckInCommand(LicensePlate licensePlate ,double weight ,  LocalDateTime time )  {
}

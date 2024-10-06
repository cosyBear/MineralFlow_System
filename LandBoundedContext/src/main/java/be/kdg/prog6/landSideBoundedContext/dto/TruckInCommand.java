package be.kdg.prog6.landSideBoundedContext.dto;

import be.kdg.prog6.landSideBoundedContext.domain.LicensePlate;

import java.time.LocalDateTime;

public record TruckInCommand(LicensePlate licensePlate ,double weight ,  LocalDateTime time )  {
}

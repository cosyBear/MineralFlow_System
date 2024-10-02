package be.kdg.prog6.LandSideBoundedContext.dto;

import be.kdg.prog6.LandSideBoundedContext.domain.LicensePlate;

import java.io.Serializable;
import java.time.LocalDateTime;

public record TruckInCommand(LicensePlate licensePlate ,double weight ,  LocalDateTime time )  {
}

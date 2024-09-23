package be.kdg.prog6.LandSideBoundedContext.port.in;

import be.kdg.prog6.LandSideBoundedContext.domain.LicensePlate;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record GateCommand(LicensePlate licensePlate, LocalDateTime localDate) {


}

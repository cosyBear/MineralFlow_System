package be.kdg.prog6.landSideBoundedContext.port.in;

import be.kdg.prog6.landSideBoundedContext.domain.LicensePlate;

import java.time.LocalDateTime;

public record GateCommand(LicensePlate licensePlate, LocalDateTime localDate) {


}

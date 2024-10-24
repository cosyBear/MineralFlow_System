package be.kdg.prog6.landSideBoundedContext.port.in;

import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import be.kdg.prog6.landSideBoundedContext.domain.LicensePlate;
import domain.MaterialType;

import java.time.LocalDateTime;

public record ScheduleAppointmentCommand(
        LicensePlate licensePlate,
        MaterialType materialType,
        LocalDateTime time,
        SellerId sellerId) {

}
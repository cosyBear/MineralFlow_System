package be.kdg.prog6.landSideBoundedContext.port.in;

import be.kdg.prog6.landSideBoundedContext.domain.LicensePlate;
import be.kdg.prog6.landSideBoundedContext.domain.MaterialType;
import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;

import java.time.LocalDateTime;

public record ScheduleAppointmentCommand(
        LicensePlate licensePlate,
        MaterialType materialType,
        LocalDateTime time,
        SellerId sellerId,
        double payload
) {

}
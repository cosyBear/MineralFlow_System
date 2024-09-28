package be.kdg.prog6.LandSideBoundedContext.port.in;

import be.kdg.prog6.LandSideBoundedContext.domain.LicensePlate;
import be.kdg.prog6.LandSideBoundedContext.domain.MaterialType;
import be.kdg.prog6.LandSideBoundedContext.domain.SellerId;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ScheduleAppointmentCommand(
        LicensePlate licensePlate,
        MaterialType materialType,
        LocalDateTime time,
        SellerId sellerId
) {
public ScheduleAppointmentCommand{

}
}
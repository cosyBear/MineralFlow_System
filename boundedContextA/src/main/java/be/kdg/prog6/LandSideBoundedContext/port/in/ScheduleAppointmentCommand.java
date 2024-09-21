package be.kdg.prog6.LandSideBoundedContext.port.in;

import java.time.LocalDate;

public record ScheduleAppointmentCommand(
        String licensePlate,
        double payload,
        String materialType,
        LocalDate date,
        Integer earliestArrivalTime,
        Integer latestArrivalTime,
        String companySeller
) {}
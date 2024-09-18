package be.kdg.prog6.LandSideBoundedContext.Port.in;

import java.time.LocalDate;

public record ScheduleAppointmentCommand(
        String sellerId,
        String licensePlate,
        double payload,
        String materialType,
        LocalDate date,
        Integer earliestArrivalTime,
        Integer latestArrivalTime
) {}
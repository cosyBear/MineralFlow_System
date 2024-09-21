package be.kdg.prog6.LandSideBoundedContext.port.out;

import be.kdg.prog6.LandSideBoundedContext.domain.Calendar;

import java.time.LocalDate;

public interface CalendarLoadPort {

    public Calendar loadAppointmentsByDate(LocalDate date);

    public Calendar loadAppointmentsByDateAndTimeSlot(LocalDate date, Integer earliestArrivalTime, Integer latestArrivalTime);

    public Calendar  loadAppointmentsByTimeSlot(Integer earliestArrivalTime, Integer latestArrivalTime);


}

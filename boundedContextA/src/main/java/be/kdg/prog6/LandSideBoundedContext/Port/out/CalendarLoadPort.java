package be.kdg.prog6.LandSideBoundedContext.Port.out;

import be.kdg.prog6.LandSideBoundedContext.domain.Appointment;
import be.kdg.prog6.LandSideBoundedContext.domain.Calendar;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface CalendarLoadPort {

    public Calendar loadAppointmentsByDate(LocalDate date);

    public Calendar loadAppointmentsByDateAndTimeSlot(LocalDate date, Integer earliestArrivalTime, Integer latestArrivalTime);

    public Calendar  loadAppointmentsByTimeSlot(Integer earliestArrivalTime, Integer latestArrivalTime);


}

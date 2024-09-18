package be.kdg.prog6.LandSideBoundedContext.Port.out;

import be.kdg.prog6.LandSideBoundedContext.domain.Appointment;
import be.kdg.prog6.LandSideBoundedContext.domain.Calendar;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface CalendarLoadPort {

    public List<Appointment> loadAppointmentsByDate(LocalDate date);

    public List<Appointment> loadAppointmentsByDateAndTimeSlot(LocalDate date, Integer earliestArrivalTime, Integer latestArrivalTime);

    public List<Appointment> loadAppointmentsByTimeSlot(Integer earliestArrivalTime, Integer latestArrivalTime);

    Calendar loadAppointmentsIntoCalendar(); // Load all appointments into the calendar

}

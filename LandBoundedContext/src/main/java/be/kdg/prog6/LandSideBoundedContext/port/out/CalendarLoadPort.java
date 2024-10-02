package be.kdg.prog6.LandSideBoundedContext.port.out;

import be.kdg.prog6.LandSideBoundedContext.domain.DayCalendar;

import java.time.LocalDate;

public interface CalendarLoadPort {

    public DayCalendar loadAppointmentsByDate(LocalDate date);


}

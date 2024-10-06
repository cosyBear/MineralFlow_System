package be.kdg.prog6.landSideBoundedContext.port.out;

import be.kdg.prog6.landSideBoundedContext.domain.DayCalendar;

import java.time.LocalDate;

public interface CalendarLoadPort {

     DayCalendar loadAppointmentsByDate(LocalDate date);




}

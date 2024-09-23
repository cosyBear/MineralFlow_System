package be.kdg.prog6.LandSideBoundedContext.port.out;

import be.kdg.prog6.LandSideBoundedContext.domain.Calendar;

import java.time.LocalDate;

public interface CalendarLoadPort {

    public Calendar loadAppointmentsByDate(LocalDate date);


}

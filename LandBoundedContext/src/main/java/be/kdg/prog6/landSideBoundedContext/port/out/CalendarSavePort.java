package be.kdg.prog6.landSideBoundedContext.port.out;

import be.kdg.prog6.landSideBoundedContext.domain.DayCalendar;

public interface CalendarSavePort {

    public void saveDayCalendar(DayCalendar dayCalendar);
}

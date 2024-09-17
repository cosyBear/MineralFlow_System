package be.kdg.prog6.LandSideBoundedContext.Port.in;// Port (interface) for the Landside bounded context

import be.kdg.prog6.LandSideBoundedContext.Dto.ScheduleAppointmentCommand;


public interface ScheduleAppointmentPort {
    void scheduleAppointment(ScheduleAppointmentCommand scheduleAppointmentCommand);
}

package be.kdg.prog6.LandSideBoundedContext.Port.in;// Port (interface) for the Landside bounded context


public interface ScheduleAppointmentPort {
    ScheduleAppointmentCommand scheduleAppointment(ScheduleAppointmentCommand scheduleAppointmentCommand);
}

package be.kdg.prog6.LandSideBoundedContext.port.in;// Port (interface) for the Landside bounded context


public interface ScheduleAppointmentUseCase {
    ScheduleAppointmentCommand scheduleAppointment(ScheduleAppointmentCommand scheduleAppointmentCommand);
}

package be.kdg.prog6.LandSideBoundedContext.port.in;// Port (interface) for the Landside bounded context


// erenME ScheduleAppointmentUseCase
public interface ScheduleAppointmentPort {
    ScheduleAppointmentCommand scheduleAppointment(ScheduleAppointmentCommand scheduleAppointmentCommand);
}

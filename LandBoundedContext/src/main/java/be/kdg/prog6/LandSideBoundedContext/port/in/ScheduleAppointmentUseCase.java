package be.kdg.prog6.LandSideBoundedContext.port.in;// Port (interface) for the Landside bounded context


import be.kdg.prog6.LandSideBoundedContext.domain.Appointment;

public interface ScheduleAppointmentUseCase {
    Appointment scheduleAppointment(ScheduleAppointmentCommand scheduleAppointmentCommand);
}

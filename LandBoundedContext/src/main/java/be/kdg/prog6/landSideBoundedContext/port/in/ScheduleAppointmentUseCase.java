package be.kdg.prog6.landSideBoundedContext.port.in;// Port (interface) for the Landside bounded context


import be.kdg.prog6.landSideBoundedContext.domain.Appointment;

public interface ScheduleAppointmentUseCase {
    Appointment scheduleAppointment(ScheduleAppointmentCommand scheduleAppointmentCommand);
}

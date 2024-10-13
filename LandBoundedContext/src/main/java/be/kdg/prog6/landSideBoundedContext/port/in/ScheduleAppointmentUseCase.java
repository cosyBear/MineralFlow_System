package be.kdg.prog6.landSideBoundedContext.port.in;// Port (interface) for the Landside bounded context


import be.kdg.prog6.landSideBoundedContext.domain.Appointment;
import be.kdg.prog6.landSideBoundedContext.domain.ScheduleAppointmentCommand;
@FunctionalInterface

public interface ScheduleAppointmentUseCase {
    Appointment scheduleAppointment(ScheduleAppointmentCommand scheduleAppointmentCommand);
}

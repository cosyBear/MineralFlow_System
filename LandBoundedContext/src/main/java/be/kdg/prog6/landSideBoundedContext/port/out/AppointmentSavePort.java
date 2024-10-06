package be.kdg.prog6.landSideBoundedContext.port.out;

import be.kdg.prog6.landSideBoundedContext.domain.Appointment;

public interface AppointmentSavePort {

    void saveAppointment(Appointment Appointment) throws Exception;
    void deleteAppointment(Appointment Appointment);
}

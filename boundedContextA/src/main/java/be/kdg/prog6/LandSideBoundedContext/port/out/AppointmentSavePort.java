package be.kdg.prog6.LandSideBoundedContext.port.out;

import be.kdg.prog6.LandSideBoundedContext.adapters.out.entity.AppointmentEntity;

public interface AppointmentSavePort {

    void saveAppointment(AppointmentEntity AppointmentEntity);
}

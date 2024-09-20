package be.kdg.prog6.LandSideBoundedContext.Port.out;

import be.kdg.prog6.LandSideBoundedContext.adapters.out.entity.AppointmentEntity;

public interface CalendarSavePort {

    void SaveAppointment(AppointmentEntity AppointmentEntity);
}

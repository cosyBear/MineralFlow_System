package be.kdg.prog6.LandSideBoundedContext.adapters.out.persistence;

import be.kdg.prog6.LandSideBoundedContext.Port.in.ScheduleAppointmentPort;
import be.kdg.prog6.LandSideBoundedContext.Port.out.CalendarSavePort;
import be.kdg.prog6.LandSideBoundedContext.adapters.out.entity.AppointmentEntity;
import be.kdg.prog6.LandSideBoundedContext.adapters.out.entity.TimeSlotEntity;
import be.kdg.prog6.LandSideBoundedContext.adapters.out.entity.TruckEntity;
import be.kdg.prog6.LandSideBoundedContext.domain.Appointment;
import be.kdg.prog6.LandSideBoundedContext.domain.Calendar;
import be.kdg.prog6.LandSideBoundedContext.domain.TimeSlot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CalendarSavePortCommand implements CalendarSavePort {

    private final AppointmentRepository appointmentRepository;
    private final ModelMapper modelMapper;
    private static final Logger logger = LogManager.getLogger(ScheduleAppointmentPort.class);

    public CalendarSavePortCommand(AppointmentRepository appointmentRepository, ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void SaveAppointment(AppointmentEntity AppointmentEntity) {
        try {
            appointmentRepository.save(AppointmentEntity);
        }
        catch (Exception e) {
            logger.debug(e.getMessage() + " ERROR from saveAppointment in the CalendarSavePortCommand class ");

        }
    }

}

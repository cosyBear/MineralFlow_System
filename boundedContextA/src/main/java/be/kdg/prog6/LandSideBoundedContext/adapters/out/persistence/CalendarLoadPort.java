package be.kdg.prog6.LandSideBoundedContext.adapters.out.persistence;

import be.kdg.prog6.LandSideBoundedContext.port.in.ScheduleAppointmentUseCase;
import be.kdg.prog6.LandSideBoundedContext.adapters.out.entity.AppointmentEntity;
import be.kdg.prog6.LandSideBoundedContext.domain.Appointment;
import  be.kdg.prog6.LandSideBoundedContext.domain.Calendar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class CalendarLoadPort implements be.kdg.prog6.LandSideBoundedContext.port.out.CalendarLoadPort {

    private final AppointmentRepository appointmentRepository;
    private final ModelMapper modelMapper;
    private static final Logger logger = LogManager.getLogger(ScheduleAppointmentUseCase.class);

    public CalendarLoadPort(AppointmentRepository appointmentRepository, ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Calendar loadAppointmentsByDate(LocalDate date) {
        List<AppointmentEntity> entities = appointmentRepository.findAppointmentsByDate(date);
        return createCalendarFromEntities(entities);
    }

    private Calendar createCalendarFromEntities(List<AppointmentEntity> entities) {
        Calendar calendar = new Calendar();
        List<Appointment> appointments = new ArrayList<>();
        for (AppointmentEntity entity : entities) {
            appointments.add(modelMapper.map(entity, Appointment.class));
        }
        calendar.setAppointments(appointments);
        return calendar;
    }
}





package be.kdg.prog6.landSideBoundedContext.adapters.out.persistence.appointmentAndCalendar;

import be.kdg.prog6.landSideBoundedContext.port.in.ScheduleAppointmentUseCase;
import be.kdg.prog6.landSideBoundedContext.adapters.out.entity.AppointmentEntity;
import be.kdg.prog6.landSideBoundedContext.domain.Appointment;
import be.kdg.prog6.landSideBoundedContext.domain.DayCalendar;
import be.kdg.prog6.landSideBoundedContext.port.out.CalendarLoadPort;
import be.kdg.prog6.landSideBoundedContext.port.out.CalendarSavePort;
import be.kdg.prog6.landSideBoundedContext.util.errorClasses.AppointmentDontExistException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
public class CalendarDataBaseAdapter implements CalendarLoadPort, CalendarSavePort {

    private final AppointmentRepository appointmentRepository;
    private final ModelMapper modelMapper;
    private static final Logger logger = LogManager.getLogger(ScheduleAppointmentUseCase.class);

    @PersistenceContext
    private EntityManager entityManager;  // Inject the EntityManager

    public CalendarDataBaseAdapter(AppointmentRepository appointmentRepository, @Qualifier("landModelMapper") ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DayCalendar loadAppointmentsByDate(LocalDate date) {
        List<AppointmentEntity> entities = appointmentRepository.findAppointmentsByDate(date);
        if (entities.isEmpty()) {
            return new DayCalendar(date, new ArrayList<>());
        }
        return createCalendarFromEntities(entities);
    }


    @Override
    public void saveDayCalendar(DayCalendar dayCalendar) {
        try {

            for (Appointment appointment : dayCalendar.getAppointments()) {
                AppointmentEntity appointmentEntity = modelMapper.map(appointment, AppointmentEntity.class);
                entityManager.merge(appointmentEntity);
            }
        } catch (Exception e) {
            logger.error("Error saving DayCalendar appointments: " + e.getMessage());
            throw e;
        }
    }


    @Override
    public Integer fetchTrucksOnSite(LocalDate time) {
        return appointmentRepository.fetchTrucksOnSite(time);

    }

    @Override
    public DayCalendar fetchTrucksOnTime(LocalDate time) {
        List<AppointmentEntity> appointmentEntities = appointmentRepository.fetchTrucksOnTime(time);
        if (appointmentEntities.isEmpty()) {
            throw new AppointmentDontExistException("No trucks found on time for date: " + time);
        }
        return createCalendarFromEntities(appointmentEntities);
    }

    private DayCalendar createCalendarFromEntities(List<AppointmentEntity> entities) {
        DayCalendar dayCalendar = new DayCalendar();
        List<Appointment> appointments = new ArrayList<>();
        for (AppointmentEntity entity : entities) {
            appointments.add(modelMapper.map(entity, Appointment.class));
        }
        dayCalendar.setAppointments(appointments);
        return dayCalendar;
    }

}





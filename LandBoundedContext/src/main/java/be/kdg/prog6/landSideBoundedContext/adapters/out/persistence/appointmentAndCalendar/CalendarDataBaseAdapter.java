package be.kdg.prog6.landSideBoundedContext.adapters.out.persistence.appointmentAndCalendar;

import be.kdg.prog6.landSideBoundedContext.port.in.ScheduleAppointmentUseCase;
import be.kdg.prog6.landSideBoundedContext.adapters.out.entity.AppointmentEntity;
import be.kdg.prog6.landSideBoundedContext.domain.Appointment;
import be.kdg.prog6.landSideBoundedContext.domain.DayCalendar;
import be.kdg.prog6.landSideBoundedContext.port.out.CalendarLoadPort;
import be.kdg.prog6.landSideBoundedContext.port.out.CalendarSavePort;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
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

    public CalendarDataBaseAdapter(AppointmentRepository appointmentRepository, ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public DayCalendar loadAppointmentsByDate(LocalDate date) {
        // add some error handling if the apAppointment dont exist throw it to the controller to catch it
        List<AppointmentEntity> entities = appointmentRepository.findAppointmentsByDate(date);
        return createCalendarFromEntities(entities);
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

    @Override
    @Transactional
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


    @Transactional(readOnly = true)
    public DayCalendar fetchTrucksOnSite(LocalDate time ) {
        List<AppointmentEntity> appointmentEntities = appointmentRepository.fetchTrucksOnSite(time);
        return createCalendarFromEntities(appointmentEntities);

    }

}





package be.kdg.prog6.landSideBoundedContext.adapters.out.persistence.appointmentAndCalendar;

import be.kdg.prog6.landSideBoundedContext.adapters.out.entity.AppointmentStatusEntity;
import be.kdg.prog6.landSideBoundedContext.adapters.out.entity.MaterialTypeEntity;
import be.kdg.prog6.landSideBoundedContext.domain.AppointmentStatus;
import be.kdg.prog6.landSideBoundedContext.port.in.ScheduleAppointmentUseCase;
import be.kdg.prog6.landSideBoundedContext.adapters.out.entity.AppointmentEntity;
import be.kdg.prog6.landSideBoundedContext.domain.Appointment;
import be.kdg.prog6.landSideBoundedContext.domain.DayCalendar;
import be.kdg.prog6.landSideBoundedContext.port.out.CalendarLoadPort;
import be.kdg.prog6.landSideBoundedContext.port.out.CalendarSavePort;
import org.springframework.stereotype.Repository;
import util.errorClasses.AppointmentDontExistException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Repository
public class CalendarDataBaseAdapter implements CalendarLoadPort, CalendarSavePort {

    private final AppointmentRepository appointmentRepository;
    private final ModelMapper modelMapper;
    private static final Logger logger = LogManager.getLogger(CalendarDataBaseAdapter.class);

    @PersistenceContext
    private EntityManager entityManager;

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
        for (Appointment appointment : dayCalendar.getAppointments()) {
            AppointmentEntity existingEntity = appointmentRepository
                    .findBySellerIdAndMaterialTypeAndLicensePlate(
                            appointment.getSellerId().id(),
                            MaterialTypeEntity.valueOf(appointment.getMaterialType().toString()),
                            appointment.getLicensePlate().toString()
                    );
            if (existingEntity == null) {
                appointmentRepository.save(modelMapper.map(appointment, AppointmentEntity.class));
            } else {
                existingEntity.setStatus(AppointmentStatusEntity.valueOf(appointment.getStatus().toString()));
                existingEntity.setTime(appointment.getTime());
                existingEntity.setLicensePlate(appointment.getLicensePlate().licensePlate());
                appointmentRepository.save(existingEntity);
            }
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





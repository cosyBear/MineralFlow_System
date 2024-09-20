package be.kdg.prog6.LandSideBoundedContext.adapters.out.persistence;

import be.kdg.prog6.LandSideBoundedContext.Port.in.ScheduleAppointmentPort;
import be.kdg.prog6.LandSideBoundedContext.Port.out.CalendarLoadPort;
import be.kdg.prog6.LandSideBoundedContext.adapters.out.entity.AppointmentEntity;
import be.kdg.prog6.LandSideBoundedContext.domain.Appointment;
import be.kdg.prog6.LandSideBoundedContext.domain.MaterialType;
import be.kdg.prog6.LandSideBoundedContext.domain.TimeSlot;
import be.kdg.prog6.LandSideBoundedContext.domain.Truck;
import  be.kdg.prog6.LandSideBoundedContext.domain.Calendar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class CalendarLoadPortQuery implements CalendarLoadPort {

    private final AppointmentRepository appointmentRepository;
    private final ModelMapper modelMapper;
    private static final Logger logger = LogManager.getLogger(ScheduleAppointmentPort.class);

    public CalendarLoadPortQuery(AppointmentRepository appointmentRepository, ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Calendar loadAppointmentsByDate(LocalDate date) {
        List<AppointmentEntity> entities = appointmentRepository.findAppointmentsByDate(date);
        return createCalendarFromEntities(entities);
    }

    @Override
    public Calendar loadAppointmentsByDateAndTimeSlot(LocalDate date, Integer earliestArrivalTime, Integer latestArrivalTime) {
        List<AppointmentEntity> entities = appointmentRepository.findAppointmentsByDateAndTimeSlot(date, earliestArrivalTime, latestArrivalTime);
        return createCalendarFromEntities(entities);
    }

    @Override
    public Calendar loadAppointmentsByTimeSlot(Integer earliestArrivalTime, Integer latestArrivalTime) {
        List<AppointmentEntity> entities = appointmentRepository.findAppointmentsByTimeSlot(earliestArrivalTime, latestArrivalTime);
        return createCalendarFromEntities(entities);
    }


    private Calendar createCalendarFromEntities(List<AppointmentEntity> entities) {
        Calendar calendar = new Calendar();
        calendar.setAppointments(populateCalendarMap(entities));
        return calendar;
    }

    private Map<LocalDate, Map<TimeSlot, List<Appointment>>> populateCalendarMap(List<AppointmentEntity> appointmentEntities) {
        Map<LocalDate, Map<TimeSlot, List<Appointment>>> appointmentsMap = new HashMap<>();

        for (AppointmentEntity entity : appointmentEntities) {
            Appointment appointment = modelMapper.map(entity, Appointment.class);

            LocalDate appointmentDate = appointment.getDate();
            TimeSlot timeSlot = appointment.getTimeSlot();

            appointmentsMap.computeIfAbsent(appointmentDate, k -> new HashMap<>());
            Map<TimeSlot, List<Appointment>> timeSlotMap = appointmentsMap.get(appointmentDate);
            timeSlotMap.computeIfAbsent(timeSlot, k -> new ArrayList<>());
            timeSlotMap.get(timeSlot).add(appointment);
        }

        return appointmentsMap;
    }
}





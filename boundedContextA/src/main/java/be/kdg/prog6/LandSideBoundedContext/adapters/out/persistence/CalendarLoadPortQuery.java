package be.kdg.prog6.LandSideBoundedContext.adapters.out.persistence;

import be.kdg.prog6.LandSideBoundedContext.Port.out.CalendarLoadPort;
import be.kdg.prog6.LandSideBoundedContext.adapters.out.persistence.entity.AppointmentEntity;
import be.kdg.prog6.LandSideBoundedContext.domain.Appointment;
import be.kdg.prog6.LandSideBoundedContext.domain.MaterialType;
import be.kdg.prog6.LandSideBoundedContext.domain.TimeSlot;
import be.kdg.prog6.LandSideBoundedContext.domain.Truck;
import  be.kdg.prog6.LandSideBoundedContext.domain.Calendar;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
@Service
public class CalendarLoadPortQuery implements CalendarLoadPort {


    private final AppointmentRepository appointmentRepository;
    private Calendar calendar;
    public CalendarLoadPortQuery(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }


    @Override
    public List<Appointment> loadAppointmentsByDate(LocalDate date) {
        List<AppointmentEntity> entities = appointmentRepository.findAppointmentsByDate(date);
        return mapEntitiesToAppointments(entities);
    }

    @Override
    public List<Appointment> loadAppointmentsByDateAndTimeSlot(LocalDate date, Integer earliestArrivalTime, Integer latestArrivalTime) {
        List<AppointmentEntity> entities = appointmentRepository.findAppointmentsByDateAndTimeSlot(date, earliestArrivalTime, latestArrivalTime);
        return mapEntitiesToAppointments(entities);
    }

    @Override
    public List<Appointment> loadAppointmentsByTimeSlot(Integer earliestArrivalTime, Integer latestArrivalTime) {
        List<AppointmentEntity> entities = appointmentRepository.findAppointmentsByTimeSlot(earliestArrivalTime, latestArrivalTime);
        return mapEntitiesToAppointments(entities);
    }


    @Override
    public Calendar loadAppointmentsIntoCalendar() {
        List<AppointmentEntity> appointmentEntities = appointmentRepository.findAll();
        Calendar calendar = new Calendar();
        calendar.setAppointments(populateCalendarMap(appointmentEntities));
        return calendar;
    }



    // Helper method to populate the Calendar's appointments map
    private Map<LocalDate, Map<TimeSlot, List<Appointment>>> populateCalendarMap(List<AppointmentEntity> appointmentEntities) {
        // Initialize the appointments map
        Map<LocalDate, Map<TimeSlot, List<Appointment>>> appointmentsMap = new HashMap<>();

        // Iterate through all appointment entities
        for (AppointmentEntity entity : appointmentEntities) {
            // Convert AppointmentEntity to domain Appointment
            Appointment appointment = mapToDomain(entity);

            // Extract the date and time slot from the Appointment
            LocalDate appointmentDate = appointment.getDate();   // Get the appointment date
            TimeSlot timeSlot = appointment.getTimeSlot();       // Get the time slot for the appointment

            // If the date doesn't exist in the map, create a new entry for it
            appointmentsMap.computeIfAbsent(appointmentDate, k -> new HashMap<>());

            // Get the map for the specific date
            Map<TimeSlot, List<Appointment>> timeSlotMap = appointmentsMap.get(appointmentDate);

            // If the time slot doesn't exist for this date, create a new entry for it
            timeSlotMap.computeIfAbsent(timeSlot, k -> new ArrayList<>());

            // Add the appointment to the list of appointments for this time slot
            timeSlotMap.get(timeSlot).add(appointment);
        }

        // Return the populated appointments map
        return appointmentsMap;
    }

    // Helper method to map AppointmentEntity to the domain model Appointment
    private Appointment mapToDomain(AppointmentEntity entity) {
        TimeSlot timeSlot = new TimeSlot(
                entity.getTimeSlot().getEarliestArrivalTime(),
                entity.getTimeSlot().getLatestArrivalTime()
        );

        Truck truck = new Truck(
                entity.getTruck().getLicenseNumber(),
                entity.getTruck().getPayload()
        );

        return new Appointment(
                timeSlot,
                truck,
                MaterialType.valueOf(String.valueOf(entity.getMaterialTypeEntity())),
                entity.getDate()
        );
    }

    // Helper method to map a list of AppointmentEntity to a list of domain Appointment
    private List<Appointment> mapEntitiesToAppointments(List<AppointmentEntity> entities) {
        List<Appointment> appointments = new ArrayList<>();
        for (AppointmentEntity entity : entities) {
            appointments.add(mapToDomain(entity));
        }
        return appointments;
    }

}

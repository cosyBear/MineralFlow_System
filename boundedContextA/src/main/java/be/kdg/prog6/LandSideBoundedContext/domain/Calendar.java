package be.kdg.prog6.LandSideBoundedContext.domain;

import be.kdg.prog6.LandSideBoundedContext.Port.in.ScheduleAppointmentCommand;
import be.kdg.prog6.LandSideBoundedContext.util.TimeSlotFullException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Calendar {

    private Map<LocalDate, Map<TimeSlot, List<Appointment>>> appointments;
    private static final Logger logger = LogManager.getLogger(Calendar.class);

    public Calendar() {
        this.appointments = new HashMap<>();
    }

    public Appointment scheduleAppointment(ScheduleAppointmentCommand requestDTO) {
        Truck truck = new Truck(requestDTO.licensePlate(), requestDTO.payload());

        TimeSlot timeSlot = new TimeSlot(
                requestDTO.earliestArrivalTime(),
                requestDTO.latestArrivalTime()
        );

        Appointment appointment = new Appointment(
                timeSlot,
                truck,
                MaterialType.valueOf(requestDTO.materialType()),
                requestDTO.date()
        );

        // Only return the appointment if it's successfully added
        if (addAppointment(appointment)) {
            return appointment;
        } else {
            throw new TimeSlotFullException("Failed to add appointment. Time slot may be full.");
        }
    }

    public boolean addAppointment(Appointment appointment) {
        LocalDate appointmentDate = appointment.getDate();
        TimeSlot timeSlot = appointment.getTimeSlot();

        // If the date doesn't exist in the map, create a new entry for it
        if (!appointments.containsKey(appointmentDate)) {
            appointments.put(appointmentDate, new HashMap<>());
        }

        Map<TimeSlot, List<Appointment>> appointmentsForDate = appointments.get(appointmentDate);

        // If the time slot exists for that date
        if (appointmentsForDate.containsKey(timeSlot)) {
            List<Appointment> appointments = appointmentsForDate.get(timeSlot);

            // Check if the maximum of 40 appointments has been reached
            if (appointments.size() < 40) {
                appointments.add(appointment);
                return true;  // Appointment successfully added
            } else {
                logger.info("Time slot " + timeSlot + " on " + appointmentDate + " is full. Maximum 40 appointments allowed.");
                throw new TimeSlotFullException("Time slot " + timeSlot + " on " + appointmentDate + " is full. Maximum 40 appointments allowed.");
            }
        } else {
            // If the time slot doesn't exist, create a new entry for it and add the appointment
            List<Appointment> newAppointmentsList = new ArrayList<>();
            newAppointmentsList.add(appointment);
            appointmentsForDate.put(timeSlot, newAppointmentsList);
            return true;
        }
    }


    public Map<TimeSlot, List<Appointment>> getAppointmentsForDate(LocalDate date) {
        return appointments.getOrDefault(date, new HashMap<>());
    }

    public List<Appointment> getAppointmentsForTimeSlot(LocalDate date, TimeSlot timeSlot) {
        if (appointments.containsKey(date)) {
            return appointments.get(date).getOrDefault(timeSlot, new ArrayList<>());
        }
        return new ArrayList<>();
    }


    public Map<LocalDate, Map<TimeSlot, List<Appointment>>> getAppointments() {
        return appointments;
    }

    public void setAppointments(Map<LocalDate, Map<TimeSlot, List<Appointment>>> appointments) {
        this.appointments = appointments;
    }
}

//    public Appointment scheduleAppointment(ScheduleAppointmentCommand requestDTO) {
//        Truck truck = new Truck(requestDTO.licensePlate(), requestDTO.payload());
//
//        TimeSlot timeSlot = new TimeSlot(
//                requestDTO.earliestArrivalTime(),
//                requestDTO.latestArrivalTime()
//        );
//
//        Appointment appointment = new Appointment(
//                timeSlot,
//                truck,
//                MaterialType.valueOf(requestDTO.materialType()),
//                requestDTO.date()
//
//        );
//        addAppointment(appointment);
//        return appointment;  // Return the newly created appointment
//
//    }
//
//    public void addAppointment(Appointment appointment) {
//        LocalDate appointmentDate = appointment.getDate();
//        TimeSlot timeSlot = appointment.getTimeSlot();
//
//        if (!appointments.containsKey(appointmentDate)) {
//
//            appointments.put(appointmentDate, new HashMap<>());
//        }
//
//        Map<TimeSlot, List<Appointment>> appointmentsForDate = appointments.get(appointmentDate);
//        // Check if the time slot already exists for that date
//        if (appointmentsForDate.containsKey(timeSlot)) {
//            List<Appointment> appointments = appointmentsForDate.get(timeSlot);
//
//            if (appointments.size() < 40) {
//                appointments.add(appointment);
//            } else {
//                logger.info("Time slot " + timeSlot + " on " + appointmentDate + " is full. Maximum 40 appointments allowed.");
//                throw new TimeSlotFullException("Time slot " + timeSlot + " on " + appointmentDate + " is full. Maximum 40 appointments allowed.");
//            }
//        } else {
//            // if the timeslot dont exist create one and add the Appointment to it.
//            List<Appointment> newAppointmentsList = new ArrayList<>();
//            newAppointmentsList.add(appointment);
//            appointmentsForDate.put(timeSlot, newAppointmentsList);
//        }
//    }
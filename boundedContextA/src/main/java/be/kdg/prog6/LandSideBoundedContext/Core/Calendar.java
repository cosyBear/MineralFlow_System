package be.kdg.prog6.LandSideBoundedContext.Core;

import be.kdg.prog6.LandSideBoundedContext.Dto.ScheduleAppointmentCommand;
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
    public void scheduleAppointment(ScheduleAppointmentCommand requestDTO){
        Truck truck = new Truck(requestDTO.licensePlate(), requestDTO.payload());

        TimeSlot timeSlot = new TimeSlot(
                requestDTO.date(),
                requestDTO.earliestArrivalTime(),
                requestDTO.latestArrivalTime()
        );

        Appointment appointment = new Appointment(
                timeSlot,
                MaterialType.valueOf(requestDTO.materialType()),
                truck
        );
        addAppointment(appointment);
    }

    public void addAppointment(Appointment appointment) {
        LocalDate appointmentDate = appointment.getTimeSlot().getDate();
        TimeSlot timeSlot = appointment.getTimeSlot();

        if (!appointments.containsKey(appointmentDate)) {

            appointments.put(appointmentDate, new HashMap<>());
        }

        Map<TimeSlot, List<Appointment>> appointmentsForDate = appointments.get(appointmentDate);
        // Check if the time slot already exists for that date
        if (appointmentsForDate.containsKey(timeSlot)) {
            List<Appointment> appointments = appointmentsForDate.get(timeSlot);

            if (appointments.size() < 40) {
                appointments.add(appointment);
            } else {
                logger.info("Time slot " + timeSlot + " on " + appointmentDate + " is full. Maximum 40 appointments allowed.");
                throw new TimeSlotFullException("Time slot " + timeSlot + " on " + appointmentDate + " is full. Maximum 40 appointments allowed.");
            }
        } else {
            // if the timeslot dont exist create one and add the Appointment to it.
            List<Appointment> newAppointmentsList = new ArrayList<>();
            newAppointmentsList.add(appointment);
            appointmentsForDate.put(timeSlot, newAppointmentsList);
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


}

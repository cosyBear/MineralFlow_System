package be.kdg.prog6.LandSideBoundedContext.Core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calendar {

    private Map<LocalDate, Map<TimeSlot, List<Appointment>>> appointments;

    public Calendar() {
        this.appointments = new HashMap<>();
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
                System.out.println("Time slot " + timeSlot + " on " + appointmentDate + " is full. Maximum 40 appointments allowed.");
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

package be.kdg.prog6.LandSideBoundedContext.domain;

import be.kdg.prog6.LandSideBoundedContext.port.in.ScheduleAppointmentCommand;
import be.kdg.prog6.LandSideBoundedContext.util.TimeSlotFullException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Calendar {


    private LocalDate date;
    private List<Appointment> appointments;
    private static final Logger logger = LogManager.getLogger(Calendar.class);


    public Calendar(LocalDate date, List<Appointment> appointments) {
        this.date = date;
        this.appointments = appointments;
    }

    public Calendar() {

    }

    public Appointment scheduleAppointment(ScheduleAppointmentCommand requestDTO) {

        Appointment appointment = new Appointment(requestDTO.materialType(), requestDTO.time(), requestDTO.sellerId(), requestDTO.licensePlate());
        if (addAppointment(appointment)) {
            return appointment;
        } else {
            throw new TimeSlotFullException("Failed to add appointment. Time slot may be full.");
        }
    }

    public boolean addAppointment(Appointment appointment) {
        if (appointments.isEmpty()) {
            appointments.add(appointment);
            return true;
        } else {
            long count = appointments.stream()
                    .filter(existingAppointment ->
                            existingAppointment.getTime().toLocalDate().equals(appointment.getTime().toLocalDate()) && // Match by day
                                    existingAppointment.getTime().getHour() == appointment.getTime().getHour()) // Match by hour
                    .count();
            if (count < 40) {
                appointments.add(appointment);
                return true;
            } else {
                throw new TimeSlotFullException("Failed to add appointment. Time slot may be full.");
            }
        }
    }

    public boolean isTruckOnTime(LocalDateTime time) {
        for (Appointment appointment : appointments) {
            if (appointment.getTime().toLocalDate().equals(time.toLocalDate()) && appointment.getTime().getHour() == time.getHour()) {
                logger.info("truck is on time ");
                return true;
            }
        }
        logger.info("truck is Not on time ");
        return false;
    }

    public boolean PassGate(LicensePlate licensePlate) {
        for (Appointment appointment : appointments) {
            if (appointment.getLicensePlate().equals(licensePlate)) {
                logger.info("PassGate is on license plate ");
                return true;
            }
        }
        logger.info("PassGate is Not on license plate ");
        return false;

    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
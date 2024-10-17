package be.kdg.prog6.landSideBoundedContext.domain;

import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import be.kdg.prog6.landSideBoundedContext.util.errorClasses.AppointmentDontExist;
import be.kdg.prog6.landSideBoundedContext.util.errorClasses.TimeSlotFullException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


public class DayCalendar {
    private LocalDate date;
    private List<Appointment> appointments;
    private static final Logger logger = LogManager.getLogger(DayCalendar.class);

    public DayCalendar(LocalDate date, List<Appointment> appointments) {
        this.date = date;
        this.appointments = appointments;
    }

    public DayCalendar() {

    }

    public Appointment getAppointmentByDate(LocalDateTime dateTime) {
        return appointments.stream()
                .filter(appointment -> appointment.getTime().isEqual(dateTime))
                .findFirst()
                .orElseThrow(() -> new AppointmentDontExist("The appointment doesn't exist."));
    }
    public Appointment appointmentDone(SellerId sellerId, LicensePlate licensePlate) {
        Optional<Appointment> matchingAppointment = appointments.stream()
                .filter(appointment -> appointment.getLicensePlate().equals(licensePlate) && appointment.getSellerId().equals(sellerId))
                .findFirst();
        return matchingAppointment.orElseThrow(() -> new AppointmentDontExist("the appointment dont exit "));
    }
    public Appointment scheduleAppointment(ScheduleAppointmentCommand requestDTO) {
        Appointment appointment = new Appointment(requestDTO.materialType(),
                requestDTO.time(), requestDTO.sellerId(), requestDTO.licensePlate()
                , AppointmentStatus.AWAITING_ARRIVAL);
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
                    .filter(existingAppointment -> existingAppointment.appointmentOnTime(appointment.getTime() , existingAppointment.getLicensePlate()))
                    .count();
            if (count < 40) {
                appointments.add(appointment);
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean truckEnters(LicensePlate licensePlate, LocalDateTime time){
        return findAppointment(licensePlate,time)
                .stream()
                .peek(Appointment::truckEnters)
                .findFirst()
                .isPresent();
    }


    private Optional<Appointment> findAppointment(LicensePlate licensePlate, LocalDateTime time) {
        for (Appointment appointment : appointments) {
            if (appointment.appointmentOnTime(time, licensePlate)) {
                return Optional.of(appointment);
            }
        }
        return Optional.empty();
    }

    public List<Appointment> getAppointments() {
        return Collections.unmodifiableList(appointments);
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}

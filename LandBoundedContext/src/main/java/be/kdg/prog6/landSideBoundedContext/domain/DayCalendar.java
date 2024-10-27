package be.kdg.prog6.landSideBoundedContext.domain;

import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import be.kdg.prog6.landSideBoundedContext.port.in.ScheduleAppointmentCommand;
import domain.MaterialType;
import util.errorClasses.AppointmentDontExistException;
import util.errorClasses.DuplicateAppointmentException;
import util.errorClasses.TimeSlotFullException;
import util.errorClasses.TruckLateException;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


@Getter
@Setter
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

    public boolean isDuplicateAppointment(Appointment appointment) {
        return appointments.stream().anyMatch(existingAppointment ->
                existingAppointment.getSellerId().equals(appointment.getSellerId()) &&
                        existingAppointment.getLicensePlate().equals(appointment.getLicensePlate()) &&
                        existingAppointment.getTime().isEqual(appointment.getTime())
        );
    }

    public Appointment appointmentDone(SellerId sellerId, LicensePlate licensePlate) {
        Optional<Appointment> matchingAppointment = appointments.stream()
                .filter(appointment -> appointment.truckLeaves(sellerId, licensePlate))
                .findFirst();
        return matchingAppointment.orElseThrow(() -> new AppointmentDontExistException("the appointment dont exit "));
    }

    public Appointment scheduleAppointment(LicensePlate licensePlate , MaterialType materialType ,LocalDateTime time,SellerId sellerId) {
        Appointment appointment = new Appointment(materialType,
                time, sellerId, licensePlate
                , AppointmentStatus.AWAITING_ARRIVAL);

        if (isDuplicateAppointment(appointment)) {
            throw new DuplicateAppointmentException("Duplicate appointment for this seller, time, and license plate.");
        }
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
                    .filter(existingAppointment -> existingAppointment.appointmentOnTime(appointment.getTime(), existingAppointment.getLicensePlate()))
                    .count();
            if (count < 40) {
                appointments.add(appointment);
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean truckEnters(LicensePlate licensePlate, LocalDateTime time) {
        Optional<Appointment> optionalAppointment = findAppointment(licensePlate, time);
        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();
            if (!appointment.isTruckOnTime(time)) {
                throw new TruckLateException("Truck with license plate " + licensePlate + " is late for its scheduled appointment.");
            }
            appointment.truckEnters();
            return true;
        }
        return false;
    }

    private Optional<Appointment> findAppointment(LicensePlate licensePlate, LocalDateTime time) {
        for (Appointment appointment : appointments) {
            if (appointment.appointmentOnTime(time, licensePlate)) {
                return Optional.of(appointment);
            }
        }
        return Optional.empty();
    }


}

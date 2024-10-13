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
        System.out.println(sellerId);
        System.out.println(licensePlate);
        Optional<Appointment> matchingAppointment = appointments.stream()
                .filter(appointment -> appointment.getLicensePlate().equals(licensePlate) && appointment.getSellerId().equals(sellerId))
                .findFirst();

        return matchingAppointment.orElseThrow(() -> new AppointmentDontExist("the appointment dont exit "));
    }
    public Appointment scheduleAppointment(ScheduleAppointmentCommand requestDTO) {


        Appointment appointment = new Appointment(requestDTO.materialType(),
                requestDTO.time(), requestDTO.sellerId(), requestDTO.licensePlate()
                , requestDTO.payload(), AppointmentStatus.AWAITING_ARRIVAL);


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
                            existingAppointment.getTime().toLocalDate().equals(appointment.getTime().toLocalDate()) &&
                                    existingAppointment.getTime().getHour() == appointment.getTime().getHour())
                    .count();
            if (count < 40) {
                appointments.add(appointment);
                return true;
            } else {
                return false;
            }
        }
    }


    public Boolean allowTruckToEnter(LicensePlate licensePlate, LocalDateTime dateAndTime) {

        Optional<Appointment> appointment = isTruckOnTime(dateAndTime);

        if (appointment.isPresent() && passGate(licensePlate)) {
            return true;
        } else
            return false;

    }

    public Optional<Appointment> isTruckOnTime(LocalDateTime time) {
        for (Appointment appointment : appointments) {

            LocalDateTime scheduledTime = appointment.getTime();

            LocalDateTime windowEndTime = scheduledTime.plusHours(1);

            if (!time.isBefore(scheduledTime) && time.isBefore(windowEndTime)) {
                logger.info("Truck is on time.");
                appointment.setAppointmentStatus(AppointmentStatus.ON_SITE);
                return Optional.of(appointment);
            } else
                appointment.setAppointmentStatus(AppointmentStatus.LATE);
        }
        return Optional.empty();
    }

    public boolean passGate(LicensePlate licensePlate) {
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
package be.kdg.prog6.landSideBoundedContext.domain;

import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import domain.MaterialType;

import java.time.LocalDateTime;
import java.util.UUID;

public class Appointment {
    private UUID appointmentId;
    private AppointmentStatus status;
    private MaterialType materialType;
    private LocalDateTime time; // replace withTime
    private SellerId sellerId;
    private LicensePlate licensePlate;

    public Appointment() {

    }

    public Appointment(MaterialType materialType, LocalDateTime date, SellerId sellerId, LicensePlate licensePlate, AppointmentStatus status) {
        this.materialType = materialType;
        this.time = date;
        this.sellerId = sellerId;
        this.licensePlate = licensePlate;
        this.status = status;
    }


    public Appointment(UUID appointmentId, AppointmentStatus status, MaterialType materialType, LocalDateTime time, SellerId sellerId, LicensePlate licensePlate) {
        this(materialType, time, sellerId, licensePlate, status);  // Constructor chaining
        this.appointmentId = appointmentId;  // Additional field initialization
    }


    public UUID getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(UUID appointmentId) {
        this.appointmentId = appointmentId;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public SellerId getSellerId() {
        return sellerId;
    }

    public void setSellerId(SellerId sellerId) {
        this.sellerId = sellerId;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(LicensePlate licensePlate) {
        this.licensePlate = licensePlate;
    }


    public AppointmentStatus getStatus() {
        return status;
    }

    public  void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public boolean appointmentOnTime(LocalDateTime scheduledTime, LicensePlate licensePlate) {
        return !time.isBefore(scheduledTime) && time.isBefore(scheduledTime.plusHours(1)) && this.getLicensePlate().equals(licensePlate);
    }


    public void truckEnters() {
        status = AppointmentStatus.ON_TIME;
    }

    public void truckLeaves() {
        this.status = AppointmentStatus.Completed;

    }
}

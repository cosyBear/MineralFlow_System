package be.kdg.prog6.landSideBoundedContext.domain;

import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import domain.MaterialType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
public class Appointment {
    private UUID appointmentId;
    private AppointmentStatus status;
    private MaterialType materialType;
    private LocalDateTime time;
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
        this(materialType, time, sellerId, licensePlate, status);
        this.appointmentId = appointmentId;
    }


    public boolean appointmentOnTime(LocalDateTime scheduledTime, LicensePlate licensePlate) {
        return !time.isBefore(scheduledTime) && time.isBefore(scheduledTime.plusHours(1)) && this.getLicensePlate().equals(licensePlate);
    }


    public void truckEnters() {
        status = AppointmentStatus.ON_SITE;
    }

    public boolean truckLeaves( SellerId sellerId,LicensePlate licensePlate ) {
        if (this.getLicensePlate().equals(licensePlate) && this.getSellerId().equals(sellerId)) {
            this.status = AppointmentStatus.Completed;
            return true;
        }else
            return false;

    }

    public boolean isTruckOnTime(LocalDateTime currentTime) {
        if (this.status == AppointmentStatus.ON_SITE) {
            return true;
        } else if (this.status == AppointmentStatus.LATE) {
            return false;
        } else if (this.status == AppointmentStatus.AWAITING_ARRIVAL) {
            return !currentTime.isAfter(this.time.plusHours(1));  // It's late
        } else return this.status == AppointmentStatus.Completed;
    }
}

package be.kdg.prog6.landSideBoundedContext.adapters.out.entity;
import be.kdg.prog6.landSideBoundedContext.domain.AppointmentStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "appointments" , catalog = "app_db"
)
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String licensePlate;

    private UUID sellerId;

    @Enumerated(EnumType.STRING)
    private MaterialTypeEntity MaterialTypeEntity;

    private LocalDateTime time; // replace withTime
    private double payload;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus AppointmentStatus;

    public AppointmentEntity() {}


    public AppointmentEntity(UUID id, String licensePlate, UUID sellerId, be.kdg.prog6.landSideBoundedContext.adapters.out.entity.MaterialTypeEntity materialTypeEntity, LocalDateTime time, double payload, AppointmentStatus AppointmentStatus) {
        this.id= id;
        this.licensePlate = licensePlate;
        this.sellerId = sellerId;
        MaterialTypeEntity = materialTypeEntity;
        this.time = time;
        this.payload = payload;
        this.AppointmentStatus = AppointmentStatus;
    }

    public be.kdg.prog6.landSideBoundedContext.domain.AppointmentStatus getAppointmentStatus() {
        return AppointmentStatus;
    }

    public void setAppointmentStatus(be.kdg.prog6.landSideBoundedContext.domain.AppointmentStatus appointmentStatus) {
        AppointmentStatus = appointmentStatus;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public UUID getSellerId() {
        return sellerId;
    }

    public void setSellerId(UUID sellerId) {
        this.sellerId = sellerId;
    }

    public be.kdg.prog6.landSideBoundedContext.adapters.out.entity.MaterialTypeEntity getMaterialTypeEntity() {
        return MaterialTypeEntity;
    }

    public void setMaterialTypeEntity(be.kdg.prog6.landSideBoundedContext.adapters.out.entity.MaterialTypeEntity materialTypeEntity) {
        MaterialTypeEntity = materialTypeEntity;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public double getPayload() {
        return payload;
    }

    public void setPayload(double payload) {
        this.payload = payload;
    }

}

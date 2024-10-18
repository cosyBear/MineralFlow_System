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


    @Enumerated(EnumType.STRING)
    private AppointmentStatusEntity status;

    public AppointmentEntity() {}


    public AppointmentEntity(UUID id, String licensePlate, UUID sellerId, MaterialTypeEntity materialTypeEntity, LocalDateTime time, AppointmentStatusEntity status) {
        this.id= id;
        this.licensePlate = licensePlate;
        this.sellerId = sellerId;
        this.MaterialTypeEntity = materialTypeEntity;
        this.time = time;
        this.status = status;
    }

    public AppointmentStatusEntity getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatusEntity status) {
        this.status = status;
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

    public MaterialTypeEntity getMaterialTypeEntity() {
        return MaterialTypeEntity;
    }

    public void setMaterialTypeEntity(MaterialTypeEntity materialTypeEntity) {
        MaterialTypeEntity = materialTypeEntity;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

}

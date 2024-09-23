package be.kdg.prog6.LandSideBoundedContext.adapters.out.entity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "appointments"
)public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String licensePlate;
    private UUID sellerId;

    @Enumerated(EnumType.STRING)
    private MaterialTypeEntity MaterialTypeEntity;

    private LocalDateTime date; // replace withTime

    public AppointmentEntity() {}

    public AppointmentEntity(Integer id, String licensePlate, UUID sellerId, be.kdg.prog6.LandSideBoundedContext.adapters.out.entity.MaterialTypeEntity materialTypeEntity, LocalDateTime date) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.sellerId = sellerId;
        MaterialTypeEntity = materialTypeEntity;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public be.kdg.prog6.LandSideBoundedContext.adapters.out.entity.MaterialTypeEntity getMaterialTypeEntity() {
        return MaterialTypeEntity;
    }

    public void setMaterialTypeEntity(be.kdg.prog6.LandSideBoundedContext.adapters.out.entity.MaterialTypeEntity materialTypeEntity) {
        MaterialTypeEntity = materialTypeEntity;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}

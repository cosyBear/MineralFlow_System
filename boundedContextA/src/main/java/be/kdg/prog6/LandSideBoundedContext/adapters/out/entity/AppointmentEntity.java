package be.kdg.prog6.LandSideBoundedContext.adapters.out.entity;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "appointments",
        uniqueConstraints = @UniqueConstraint(columnNames = {"truck_id", "date", "time_slot_id"})
)public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String companyName;

    @Enumerated(EnumType.STRING)
    private MaterialTypeEntity MaterialTypeEntity;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "time_slot_id")
    private TimeSlotEntity timeSlot;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "truck_id")
    private TruckEntity truck;
    private LocalDate date;

    public AppointmentEntity() {}

    public AppointmentEntity(MaterialTypeEntity materialType, TimeSlotEntity timeSlot, TruckEntity truck , LocalDate date, String companyName) {
        this.MaterialTypeEntity = materialType;
        this.timeSlot = timeSlot;
        this.truck = truck;
        this.date = date ;
        this.companyName = companyName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public be.kdg.prog6.LandSideBoundedContext.adapters.out.entity.MaterialTypeEntity getMaterialTypeEntity() {
        return MaterialTypeEntity;
    }

    public void setMaterialTypeEntity(be.kdg.prog6.LandSideBoundedContext.adapters.out.entity.MaterialTypeEntity materialTypeEntity) {
        MaterialTypeEntity = materialTypeEntity;
    }

    public TimeSlotEntity getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlotEntity timeSlot) {
        this.timeSlot = timeSlot;
    }

    public TruckEntity getTruck() {
        return truck;
    }

    public void setTruck(TruckEntity truck) {
        this.truck = truck;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}

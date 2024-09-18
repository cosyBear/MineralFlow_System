package be.kdg.prog6.LandSideBoundedContext.adapters.out.persistence.entity;
import be.kdg.prog6.LandSideBoundedContext.domain.MaterialType;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "appointments")
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private MaterialTypeEntity MaterialTypeEntity;

    @ManyToOne
    @JoinColumn(name = "time_slot_id")
    private TimeSlotEntity timeSlot;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "truck_id")
    private TruckEntity truck;
    private LocalDate date;  // Add the date to the AppointmentEntity

    public AppointmentEntity() {}

    public AppointmentEntity(MaterialTypeEntity materialType, TimeSlotEntity timeSlot, TruckEntity truck , LocalDate date) {
        this.MaterialTypeEntity = materialType;
        this.timeSlot = timeSlot;
        this.truck = truck;
        this.date = date ;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public be.kdg.prog6.LandSideBoundedContext.adapters.out.persistence.entity.MaterialTypeEntity getMaterialTypeEntity() {
        return MaterialTypeEntity;
    }

    public void setMaterialTypeEntity(be.kdg.prog6.LandSideBoundedContext.adapters.out.persistence.entity.MaterialTypeEntity materialTypeEntity) {
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
}

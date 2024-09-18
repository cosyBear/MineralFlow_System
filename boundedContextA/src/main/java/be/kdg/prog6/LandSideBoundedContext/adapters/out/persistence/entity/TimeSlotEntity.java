package be.kdg.prog6.LandSideBoundedContext.adapters.out.persistence.entity;

import be.kdg.prog6.LandSideBoundedContext.util.TimeSlotFullException;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "time_slots")
public class TimeSlotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer earliestArrivalTime;
    private Integer latestArrivalTime;

    @OneToMany(mappedBy = "timeSlot", cascade = CascadeType.ALL)
    private List<AppointmentEntity> appointments;

    public TimeSlotEntity() {}

    public TimeSlotEntity( Integer earliestArrivalTime, Integer latestArrivalTime) {
        this.earliestArrivalTime = earliestArrivalTime;
        this.latestArrivalTime = latestArrivalTime;
    }

    public void addAppointment(AppointmentEntity appointment) {
        if (appointments.size() >= 40) {
            throw new TimeSlotFullException("Time slot is full. Maximum 40 appointments allowed.");
        }
        appointments.add(appointment);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Integer getEarliestArrivalTime() {
        return earliestArrivalTime;
    }

    public void setEarliestArrivalTime(Integer earliestArrivalTime) {
        this.earliestArrivalTime = earliestArrivalTime;
    }

    public Integer getLatestArrivalTime() {
        return latestArrivalTime;
    }

    public void setLatestArrivalTime(Integer latestArrivalTime) {
        this.latestArrivalTime = latestArrivalTime;
    }

    public List<AppointmentEntity> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentEntity> appointments) {
        this.appointments = appointments;
    }

}

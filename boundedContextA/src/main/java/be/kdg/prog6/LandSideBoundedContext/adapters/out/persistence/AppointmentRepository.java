package be.kdg.prog6.LandSideBoundedContext.adapters.out.persistence;

import be.kdg.prog6.LandSideBoundedContext.adapters.out.persistence.entity.AppointmentEntity;
import be.kdg.prog6.LandSideBoundedContext.adapters.out.persistence.entity.TimeSlotEntity;
import be.kdg.prog6.LandSideBoundedContext.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Integer> {

    @Query("SELECT a FROM AppointmentEntity a " +
            "LEFT JOIN FETCH a.timeSlot " +
            "LEFT JOIN FETCH a.truck " +
            "WHERE a.date = :date")
    List<AppointmentEntity> findAppointmentsByDate(@Param("date") LocalDate date);
    @Query("SELECT a FROM AppointmentEntity a " +
            "LEFT JOIN FETCH a.timeSlot t " +
            "LEFT JOIN FETCH a.truck " +
            "WHERE a.date = :date " +
            "AND t.earliestArrivalTime = :earliestArrivalTime " +
            "AND t.latestArrivalTime = :latestArrivalTime")
    List<AppointmentEntity> findAppointmentsByDateAndTimeSlot(
            @Param("date") LocalDate date,
            @Param("earliestArrivalTime") Integer earliestArrivalTime,
            @Param("latestArrivalTime") Integer latestArrivalTime);
    @Query("SELECT a FROM AppointmentEntity a " +
            "LEFT JOIN FETCH a.timeSlot t " +
            "LEFT JOIN FETCH a.truck " +
            "WHERE t.earliestArrivalTime = :earliestArrivalTime " +
            "AND t.latestArrivalTime = :latestArrivalTime")
    List<AppointmentEntity> findAppointmentsByTimeSlot(
            @Param("earliestArrivalTime") Integer earliestArrivalTime,
            @Param("latestArrivalTime") Integer latestArrivalTime);

}

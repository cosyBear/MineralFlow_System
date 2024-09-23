package be.kdg.prog6.LandSideBoundedContext.adapters.out.persistence;

import be.kdg.prog6.LandSideBoundedContext.adapters.out.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Integer> {

    @Query("SELECT a FROM AppointmentEntity a " +
            "WHERE a.date = :date")
    List<AppointmentEntity> findAppointmentsByDate(@Param("date") LocalDate date);
}

package be.kdg.prog6.landSideBoundedContext.adapters.out.persistence.appointmentAndCalendar;

import be.kdg.prog6.landSideBoundedContext.adapters.out.entity.AppointmentEntity;
import be.kdg.prog6.landSideBoundedContext.adapters.out.entity.MaterialTypeEntity;
import be.kdg.prog6.landSideBoundedContext.domain.Appointment;
import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import be.kdg.prog6.landSideBoundedContext.domain.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Integer> {

    @Query("SELECT a FROM AppointmentEntity a WHERE DATE(a.time) = :date")
    List<AppointmentEntity> findAppointmentsByDate(@Param("date") LocalDate date);

    @Query("select a from AppointmentEntity  a  where a.sellerId = :sellerId  and a.MaterialTypeEntity = :materialType")
    AppointmentEntity findBySellerIdAndMaterialType(@Param("sellerId") UUID sellerId , @Param("materialType") MaterialTypeEntity materialType);
}

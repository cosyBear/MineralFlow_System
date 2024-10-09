package be.kdg.prog6.landSideBoundedContext.adapters.out.persistence.appointmentAndCalendar;

import be.kdg.prog6.landSideBoundedContext.adapters.out.entity.AppointmentEntity;
import be.kdg.prog6.landSideBoundedContext.adapters.out.entity.MaterialTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, UUID> {

    @Query("SELECT a FROM AppointmentEntity a WHERE DATE(a.time) = :date")
    List<AppointmentEntity> findAppointmentsByDate(@Param("date") LocalDate date);

    @Query("select a from AppointmentEntity  a  where a.sellerId = :sellerId  and a.MaterialTypeEntity = :materialType")
    AppointmentEntity findBySellerIdAndMaterialType(@Param("sellerId") UUID sellerId , @Param("materialType") MaterialTypeEntity materialType);

    @Query("SELECT a FROM AppointmentEntity a WHERE a.AppointmentStatus = 'ON_SITE' and DATE(a.time) = :date")
    List<AppointmentEntity> fetchTrucksOnSite(@Param("date") LocalDate date);

}


package be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence.Repository;


import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEventsWindowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface WarehouseEventsWindowEntityRepository extends JpaRepository<WarehouseEventsWindowEntity, UUID> {

    @Query("SELECT wew FROM WarehouseEventsWindowEntity wew " +
            "LEFT JOIN FETCH wew.warehouseEventList " +
            "WHERE wew.warehouseId = :warehouseId")
    Optional<WarehouseEventsWindowEntity> fetchByWarehouseIdWithEvents(@Param("warehouseId") UUID warehouseId);

    @Query("SELECT wew FROM WarehouseEventsWindowEntity wew " +
            "LEFT JOIN FETCH wew.warehouseEventList " +
            "WHERE wew.warehouseEventsWindowId = :warehouseEventsWindowId")
    Optional<WarehouseEventsWindowEntity> fetchByWarehouseEventsWindowIdWithEvents(@Param("warehouseEventsWindowId") UUID warehouseEventsWindowId);


}

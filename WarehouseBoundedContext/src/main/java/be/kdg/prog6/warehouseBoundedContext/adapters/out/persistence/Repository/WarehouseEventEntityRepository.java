package be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence.Repository;


import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEventEntity;
import be.kdg.prog6.warehouseBoundedContext.domain.MaterialType;
import be.kdg.prog6.warehouseBoundedContext.domain.SellerId;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseEventId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WarehouseEventEntityRepository extends JpaRepository<WarehouseEventEntity, UUID> {

    @Query("SELECT we FROM WarehouseEventEntity we")
    List<WarehouseEventEntity> findAllEvents();




}

package be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence.Repository;


import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEventsWindowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WarehouseEventsWindowEntityRepository extends JpaRepository<WarehouseEventsWindowEntity, UUID> {

    Optional<WarehouseEventsWindowEntity> findByWarehouseId(UUID warehouseId);
    Optional<WarehouseEventsWindowEntity> findByWarehouseEventWindowId(UUID warehouseEventWindowId);

}

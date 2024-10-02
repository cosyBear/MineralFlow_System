package be.kdg.prog6.boundedcontextB.adapters.out.persistence.Repository;


import be.kdg.prog6.boundedcontextB.adapters.out.JPAEntity.WarehouseEventsWindowEntity;
import be.kdg.prog6.boundedcontextB.domain.WarehouseId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WarehouseEventsWindowEntityRepository extends JpaRepository<WarehouseEventsWindowEntity, UUID> {

    Optional<WarehouseEventsWindowEntity> findByWarehouseId(WarehouseId warehouseId);
    Optional<WarehouseEventsWindowEntity> findWarehouseEventsWindowEntitiesByWarehouseEventWindowId(UUID warehouseEventWindowId);
}

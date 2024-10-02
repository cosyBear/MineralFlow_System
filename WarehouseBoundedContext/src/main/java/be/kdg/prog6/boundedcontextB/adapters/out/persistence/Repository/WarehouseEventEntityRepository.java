package be.kdg.prog6.boundedcontextB.adapters.out.persistence.Repository;


import be.kdg.prog6.boundedcontextB.adapters.out.JPAEntity.WarehouseEventEntity;
import be.kdg.prog6.boundedcontextB.domain.WarehouseEventId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WarehouseEventEntityRepository extends JpaRepository<WarehouseEventEntity, UUID> {

    Optional<WarehouseEventEntity> findByWareHouseEventId(WarehouseEventId id);

    Optional<WarehouseEventEntity> findByWeighBridgeTicketId(UUID weighBridgeTicketId);
}

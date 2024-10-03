package be.kdg.prog6.warehouseBoundedContext.port.out.WarehouseEvent;


import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseEvent;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseEventId;

import java.util.Optional;
import java.util.UUID;

public interface WarehouseEventLoadPort {


    Optional<WarehouseEvent> findByWarehouseEventId(WarehouseEventId id);

    Optional<WarehouseEvent> findByWeighBridgeTicketId(UUID weighBridgeTicketId);

}

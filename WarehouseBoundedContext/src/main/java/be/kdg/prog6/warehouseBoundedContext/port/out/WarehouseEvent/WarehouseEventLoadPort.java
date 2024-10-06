package be.kdg.prog6.warehouseBoundedContext.port.out.WarehouseEvent;


import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEventEntity;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseEvent;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseEventId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WarehouseEventLoadPort {


    List<WarehouseEvent> findAllEvents();


}

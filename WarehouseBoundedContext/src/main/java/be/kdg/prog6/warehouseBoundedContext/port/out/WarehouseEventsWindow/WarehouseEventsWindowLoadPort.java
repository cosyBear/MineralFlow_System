package be.kdg.prog6.warehouseBoundedContext.port.out.WarehouseEventsWindow;

import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEventsWindowEntity;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseEventsWindow;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseId;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface WarehouseEventsWindowLoadPort {

    Optional<WarehouseEventsWindowEntity> fetchByWarehouseEventsWindowIdWithEvents( UUID warehouseEventsWindowId);
    Optional<WarehouseEventsWindowEntity> fetchByWarehouseIdWithEvents( UUID warehouseId);


}

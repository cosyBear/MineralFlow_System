package be.kdg.prog6.warehouseBoundedContext.port.out;

import be.kdg.prog6.warehouseBoundedContext.domain.ShipmentCompletedEvent;
import be.kdg.prog6.warehouseBoundedContext.domain.Warehouse;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseEvent;

public interface WaterSideEventPublisher {


    void ShipmentCompleted(ShipmentCompletedEvent event);

}

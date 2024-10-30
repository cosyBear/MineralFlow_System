package be.kdg.prog6.warehouseBoundedContext.port.out;

import be.kdg.prog6.warehouseBoundedContext.port.in.ShipmentCompletedEvent;

public interface WaterSideEventPublisher {


    void ShipmentCompleted(ShipmentCompletedEvent event);

}

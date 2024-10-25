package be.kdg.prog6.warehouseBoundedContext.port.out;

public interface WaterSideEventPublisher {


    void ShipmentCompleted(ShipmentCompletedEvent event);

}

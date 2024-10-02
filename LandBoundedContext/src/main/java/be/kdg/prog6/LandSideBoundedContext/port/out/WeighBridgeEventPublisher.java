package be.kdg.prog6.LandSideBoundedContext.port.out;


import be.kdg.prog6.LandSideBoundedContext.domain.WeighInEvent;

public interface WeighBridgeEventPublisher {
     void publishTruckWeightedIn(WeighInEvent event);
    void publishTruckWeighedOut(WeighInEvent event);
}

package be.kdg.prog6.landSideBoundedContext.port.out;


import be.kdg.prog6.landSideBoundedContext.domain.WeighInEvent;
import be.kdg.prog6.landSideBoundedContext.domain.WeighEvent;

public interface WeighBridgeEventPublisher {
     void publishTruckWeightedIn(WeighEvent weighIn);
    void publishTruckWeighedOut(WeighEvent weighOut);
}

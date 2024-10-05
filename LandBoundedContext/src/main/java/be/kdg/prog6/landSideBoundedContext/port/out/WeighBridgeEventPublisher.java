package be.kdg.prog6.landSideBoundedContext.port.out;


import be.kdg.prog6.landSideBoundedContext.domain.WeighInEvent;
import be.kdg.prog6.landSideBoundedContext.domain.WeighOutEvent;

public interface WeighBridgeEventPublisher {
     void publishTruckWeightedIn(WeighInEvent weighIn);
    void publishTruckWeighedOut(WeighOutEvent weighOut);
}

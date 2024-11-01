package be.kdg.prog6.landSideBoundedContext.port.out;


import be.kdg.prog6.landSideBoundedContext.port.in.WeighInEvent;
import be.kdg.prog6.landSideBoundedContext.port.in.WeighOutEvent;

public interface WeighBridgeEventPublisher {
     void publishTruckWeightedIn(WeighInEvent weighIn);
    void publishTruckWeighedOut(WeighOutEvent weighOut);
}

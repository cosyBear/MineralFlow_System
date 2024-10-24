package be.kdg.prog6.landSideBoundedContext.port.in;


public interface WeighBridgeUseCase {


    void weighTruckIn(WeighTruckInCommand command);

    void weighTruckOut(weighTruckOutCommand command);




}

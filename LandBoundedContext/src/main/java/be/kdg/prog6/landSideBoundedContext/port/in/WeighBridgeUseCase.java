package be.kdg.prog6.landSideBoundedContext.port.in;


public interface WeighBridgeUseCase {


    String weighTruckIn(WeighTruckInCommand command);

    String weighTruckOut(weighTruckOutCommand command);




}

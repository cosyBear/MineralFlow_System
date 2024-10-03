package be.kdg.prog6.landSideBoundedContext.port.in;


public interface WeighBridgeUseCase {


        void weighTruckIn(weighTruckInCommand command);
        void weighTruckOut(weighTruckOutCommand command);



}

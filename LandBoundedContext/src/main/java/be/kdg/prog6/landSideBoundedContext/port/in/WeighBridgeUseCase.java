package be.kdg.prog6.landSideBoundedContext.port.in;


import be.kdg.prog6.landSideBoundedContext.domain.UpdateWarehouseCommand;

public interface WeighBridgeUseCase {


        void weighTruckIn(weighTruckInCommand command);
        void weighTruckOut(weighTruckOutCommand command);

        void updateWarehouse(UpdateWarehouseCommand updateWarehouseCommand);



}

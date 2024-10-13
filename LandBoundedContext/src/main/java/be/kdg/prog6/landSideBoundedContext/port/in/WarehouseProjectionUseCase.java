package be.kdg.prog6.landSideBoundedContext.port.in;

import be.kdg.prog6.landSideBoundedContext.domain.UpdateWarehouseCommand;
@FunctionalInterface

public interface WarehouseProjectionUseCase {


     void updateWarehouse(UpdateWarehouseCommand updateWarehouseCommand);

}

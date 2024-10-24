package be.kdg.prog6.landSideBoundedContext.port.in;

@FunctionalInterface

public interface WarehouseProjectionUseCase {


     void updateWarehouse(UpdateWarehouseCommand updateWarehouseCommand);

}

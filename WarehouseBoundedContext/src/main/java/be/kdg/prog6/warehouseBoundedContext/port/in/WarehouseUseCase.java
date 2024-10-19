package be.kdg.prog6.warehouseBoundedContext.port.in;

public interface WarehouseUseCase {


    void truckIn(WeighTruckInCommand weighTruckInCommand);
    void truckOut(WeighTruckOutCommand weighTruckOutCommand);

}

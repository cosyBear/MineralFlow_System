package be.kdg.prog6.warehouseBoundedContext.port.in;

import be.kdg.prog6.warehouseBoundedContext.domain.WeighTruckInCommand;
import be.kdg.prog6.warehouseBoundedContext.domain.WeighTruckOutCommand;

public interface WarehouseUseCase {


    void truckIn(WeighTruckInCommand weighTruckInCommand);
    void truckOut(WeighTruckOutCommand weighTruckOutCommand);

}

package be.kdg.prog6.warehouseBoundedContext.port.in;

import be.kdg.prog6.warehouseBoundedContext.domain.WeighTruckCommand;

public interface WarehouseUseCase {


    void truckIn(WeighTruckCommand weighTruckCommand);
    void truckOut(WeighTruckCommand weighTruckCommand);

}

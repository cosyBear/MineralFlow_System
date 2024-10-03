package be.kdg.prog6.warehouseBoundedContext.port.in;

import be.kdg.prog6.warehouseBoundedContext.domain.weighTruckCommand;
import be.kdg.prog6.warehouseBoundedContext.domain.weighTruckOutCommand;

public interface WarehouseUseCase {


    void truckIn(weighTruckCommand weighTruckCommand);
    void truckOut(weighTruckCommand weighTruckCommand);

}

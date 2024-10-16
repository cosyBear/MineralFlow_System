package be.kdg.prog6.landSideBoundedContext.port.in;


import be.kdg.prog6.landSideBoundedContext.domain.WeighTruckInCommand;
import be.kdg.prog6.landSideBoundedContext.domain.weighTruckOutCommand;
import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import be.kdg.prog6.landSideBoundedContext.domain.WarehouseStatus;
import domain.MaterialType;

public interface WeighBridgeUseCase {


    void weighTruckIn(WeighTruckInCommand command);

    void weighTruckOut(weighTruckOutCommand command);




}

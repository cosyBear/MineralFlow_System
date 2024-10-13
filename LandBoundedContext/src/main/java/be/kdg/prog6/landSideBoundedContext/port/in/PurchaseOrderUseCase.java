package be.kdg.prog6.landSideBoundedContext.port.in;

import be.kdg.prog6.landSideBoundedContext.domain.PurchaseOrderCommand;
@FunctionalInterface

public interface PurchaseOrderUseCase {

    void CreatePurchaseOrder(PurchaseOrderCommand purchaseOrderCommand);
}

package be.kdg.prog6.landSideBoundedContext.port.out;

import be.kdg.prog6.landSideBoundedContext.domain.PurchaseOrder;
@FunctionalInterface

public interface PurchaseOrderSavePort {
    PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder);

}

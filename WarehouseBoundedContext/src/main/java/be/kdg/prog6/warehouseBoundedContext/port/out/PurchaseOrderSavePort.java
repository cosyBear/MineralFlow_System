package be.kdg.prog6.warehouseBoundedContext.port.out;

import be.kdg.prog6.warehouseBoundedContext.domain.PurchaseOrder;

@FunctionalInterface

public interface PurchaseOrderSavePort {
    PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder);

}

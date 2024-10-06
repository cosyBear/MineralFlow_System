package be.kdg.prog6.landSideBoundedContext.port.out;

import be.kdg.prog6.landSideBoundedContext.domain.PurchaseOrder;

public interface PurchaseOrderSavePort {
    PurchaseOrder save(PurchaseOrder purchaseOrder);

}

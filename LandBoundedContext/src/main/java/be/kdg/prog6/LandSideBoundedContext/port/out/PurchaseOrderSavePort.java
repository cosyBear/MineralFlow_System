package be.kdg.prog6.LandSideBoundedContext.port.out;

import be.kdg.prog6.LandSideBoundedContext.domain.PurchaseOrder;

public interface PurchaseOrderSavePort {
    PurchaseOrder save(PurchaseOrder purchaseOrder);

}

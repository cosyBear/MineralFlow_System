package be.kdg.prog6.warehouseBoundedContext.port.in;


import be.kdg.prog6.warehouseBoundedContext.domain.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderUseCase {

    void createPurchaseOrder(PurchaseOrderCommand purchaseOrderCommand);


    List<PurchaseOrder> getPurchaseOrderStatus();

}

package be.kdg.prog6.warehouseBoundedContext.port.in;


import be.kdg.prog6.warehouseBoundedContext.domain.PurchaseOrder;
import be.kdg.prog6.warehouseBoundedContext.domain.PurchaseOrderCommand;
import be.kdg.prog6.warehouseBoundedContext.domain.PurchaseOrderLine;

import java.util.List;
import java.util.UUID;

public interface PurchaseOrderUseCase {

    void createPurchaseOrder(PurchaseOrderCommand purchaseOrderCommand);


    List<PurchaseOrder> getPurchaseOrderStatus();

}

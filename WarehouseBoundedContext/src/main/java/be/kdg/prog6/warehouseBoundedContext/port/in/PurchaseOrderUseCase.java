package be.kdg.prog6.warehouseBoundedContext.port.in;


import be.kdg.prog6.warehouseBoundedContext.domain.PurchaseOrderCommand;

@FunctionalInterface
public interface PurchaseOrderUseCase {

    void createPurchaseOrder(PurchaseOrderCommand purchaseOrderCommand);
}

package be.kdg.prog6.landSideBoundedContext.core;

import be.kdg.prog6.landSideBoundedContext.domain.PurchaseOrderCommand;
import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import be.kdg.prog6.landSideBoundedContext.domain.PurchaseOrder;
import be.kdg.prog6.landSideBoundedContext.port.in.PurchaseOrderUseCase;
import be.kdg.prog6.landSideBoundedContext.port.out.PurchaseOrderLoadPort;
import be.kdg.prog6.landSideBoundedContext.port.out.PurchaseOrderSavePort;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderUseCaseImp implements PurchaseOrderUseCase {


    private final PurchaseOrderLoadPort purchaseOrderLoadPort;
    private final PurchaseOrderSavePort purchaseOrderSavePort;

    public PurchaseOrderUseCaseImp(PurchaseOrderLoadPort purchaseOrderLoadPort, PurchaseOrderSavePort purchaseOrderSavePort) {
        this.purchaseOrderLoadPort = purchaseOrderLoadPort;
        this.purchaseOrderSavePort = purchaseOrderSavePort;
    }


    @Override
    public void CreatePurchaseOrder(PurchaseOrderCommand purchaseOrderCommand) {

        purchaseOrderSavePort.createPurchaseOrder(mapToEntity(purchaseOrderCommand));

    }


    public PurchaseOrder mapToEntity(PurchaseOrderCommand purchaseOrderCommand) {

        return new PurchaseOrder(purchaseOrderCommand.orderDate(),
                new SellerId(purchaseOrderCommand.sellerId()), purchaseOrderCommand.customerName(), purchaseOrderCommand.materialType(),
                purchaseOrderCommand.amountOfMaterialInTons());
    }
}

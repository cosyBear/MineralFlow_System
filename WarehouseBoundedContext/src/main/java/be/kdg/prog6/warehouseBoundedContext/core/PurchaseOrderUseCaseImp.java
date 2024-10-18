package be.kdg.prog6.warehouseBoundedContext.core;

import be.kdg.prog6.warehouseBoundedContext.domain.PurchaseOrder;
import be.kdg.prog6.warehouseBoundedContext.domain.PurchaseOrderCommand;
import be.kdg.prog6.warehouseBoundedContext.domain.PurchaseOrderLine;
import be.kdg.prog6.warehouseBoundedContext.domain.SellerId;
import be.kdg.prog6.warehouseBoundedContext.port.in.PurchaseOrderUseCase;
import be.kdg.prog6.warehouseBoundedContext.port.out.PurchaseOrderLoadPort;
import be.kdg.prog6.warehouseBoundedContext.port.out.PurchaseOrderSavePort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseOrderUseCaseImp implements PurchaseOrderUseCase {


    private final PurchaseOrderLoadPort purchaseOrderLoadPort;
    private final PurchaseOrderSavePort purchaseOrderSavePort;

    public PurchaseOrderUseCaseImp(PurchaseOrderLoadPort purchaseOrderLoadPort, PurchaseOrderSavePort purchaseOrderSavePort) {
        this.purchaseOrderLoadPort = purchaseOrderLoadPort;
        this.purchaseOrderSavePort = purchaseOrderSavePort;
    }

    @Override
    public void createPurchaseOrder(PurchaseOrderCommand purchaseOrderCommand) {
        purchaseOrderSavePort.createPurchaseOrder(mapToEntity(purchaseOrderCommand));
    }


    public PurchaseOrder mapToEntity(PurchaseOrderCommand purchaseOrderCommand) {
        PurchaseOrder purchaseOrder = new PurchaseOrder(purchaseOrderCommand.orderDate(),
                new SellerId(purchaseOrderCommand.sellerId()), purchaseOrderCommand.customerName());


        List<PurchaseOrderLine> orderLine = purchaseOrderCommand.orderlineList().stream().map(item -> {
            return new PurchaseOrderLine(item.materialType(), item.quantity(), item.pricePerTon());
        }).toList();
        purchaseOrder.setOrderLines(orderLine);
        return purchaseOrder;
    }
}

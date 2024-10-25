package be.kdg.prog6.warehouseBoundedContext.core;

import be.kdg.prog6.warehouseBoundedContext.domain.*;
import be.kdg.prog6.warehouseBoundedContext.port.in.PurchaseOrderCommand;
import be.kdg.prog6.warehouseBoundedContext.port.in.PurchaseOrderUseCase;
import be.kdg.prog6.warehouseBoundedContext.port.out.PurchaseOrderLoadPort;
import be.kdg.prog6.warehouseBoundedContext.port.out.PurchaseOrderSavePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional

    public void createPurchaseOrder(PurchaseOrderCommand purchaseOrderCommand) {
        purchaseOrderSavePort.save(mapToEntity(purchaseOrderCommand));
    }

    @Override
    @Transactional
    public List<PurchaseOrder> getPurchaseOrderStatus() {
        return purchaseOrderLoadPort.getAllPurchaseOrdersStatus();
    }

    public PurchaseOrder mapToEntity(PurchaseOrderCommand purchaseOrderCommand) {
        PurchaseOrder purchaseOrder = new PurchaseOrder(purchaseOrderCommand.orderDate(),
                new SellerId(purchaseOrderCommand.sellerId()), purchaseOrderCommand.customerName() , purchaseOrderCommand.buyerId() , PurchaseOrderStatus.outstanding);

        List<PurchaseOrderLine> orderLine = purchaseOrderCommand.orderlineList().stream().map(item -> {
            return new PurchaseOrderLine(item.materialType(), item.quantity(), item.pricePerTon());
        }).toList();
        purchaseOrder.setOrderLines(orderLine);
        return purchaseOrder;
    }




}

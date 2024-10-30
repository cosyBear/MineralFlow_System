package be.kdg.prog6.warehouseBoundedContext.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseOrder {
    private LocalDate orderDate;

    private UUID purchaseOrderId;

    private SellerId sellerId;

    private String CustomerName;

    private UUID buyerId;

    private List<PurchaseOrderLine> orderLines = new ArrayList<>();

    private PurchaseOrderStatus status;

    public PurchaseOrder(LocalDate orderDate, UUID purchaseOrderId, SellerId sellerId, String customerName, List<PurchaseOrderLine> orderLines, PurchaseOrderStatus status, UUID buyerId) {
        this.orderDate = orderDate;
        this.purchaseOrderId = purchaseOrderId;
        this.sellerId = sellerId;
        this.CustomerName = customerName;
        this.orderLines = orderLines;
        this.status = status;
        this.buyerId = buyerId;
    }

    public PurchaseOrder(LocalDate orderDate, SellerId sellerId, String customerName, UUID buyerId, PurchaseOrderStatus status) {
        this.orderDate = orderDate;
        this.sellerId = sellerId;
        CustomerName = customerName;
        this.status = status;
        this.buyerId = buyerId;
    }

    public void updateStatus(PurchaseOrderStatus status) {
        this.status = status;
    }
    public boolean isPurchaseOrderFulfilled() {
        return this.status.equals(PurchaseOrderStatus.fulfilled);
    }


}

package be.kdg.prog6.warehouseBoundedContext.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class PurchaseOrder {
    // the date is when the order was placed.
    private LocalDate orderDate;

    private UUID purchaseOrderId;

    private SellerId sellerId;

    private String CustomerName;

    private List<PurchaseOrderLine> orderLines = new ArrayList<>();  // List of materials and quantities being purchased

    private PurchaseOrderStatus status ;
    public PurchaseOrder(LocalDate orderDate, UUID purchaseOrderId, SellerId sellerId, String customerName, List<PurchaseOrderLine> orderLines , PurchaseOrderStatus status) {
        this.orderDate = orderDate;
        this.purchaseOrderId = purchaseOrderId;
        this.sellerId = sellerId;
        this.CustomerName = customerName;
        this.orderLines = orderLines;
        this.status = status;
    }

    public PurchaseOrder(LocalDate orderDate, SellerId sellerId, String customerName , PurchaseOrderStatus status) {
        this.orderDate = orderDate;
        this.sellerId = sellerId;
        CustomerName = customerName;
        this.status = status;
    }


    public PurchaseOrderStatus getStatus() {
        return status;
    }

    public void updateStatus(PurchaseOrderStatus status) {
        this.status = status;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public UUID getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(UUID purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public SellerId getSellerId() {
        return sellerId;
    }

    public void setSellerId(SellerId sellerId) {
        this.sellerId = sellerId;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public List<PurchaseOrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<PurchaseOrderLine> orderLines) {
        this.orderLines = orderLines;
    }
}

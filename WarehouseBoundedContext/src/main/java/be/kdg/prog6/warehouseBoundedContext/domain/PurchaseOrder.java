package be.kdg.prog6.warehouseBoundedContext.domain;

import domain.MaterialType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class PurchaseOrder {
    // the date is when the order was placed.
    private LocalDate orderDate;

    private UUID purchaseOrderNumber;

    private SellerId sellerId;

    private String CustomerName;

    private List<PurchaseOrderLine> orderLines = new ArrayList<>();  // List of materials and quantities being purchased

    public PurchaseOrder(LocalDate orderDate, UUID purchaseOrderNumber, SellerId sellerId, String customerName, List<PurchaseOrderLine> orderLines) {
        this.orderDate = orderDate;
        this.purchaseOrderNumber = purchaseOrderNumber;
        this.sellerId = sellerId;
        CustomerName = customerName;
        this.orderLines = orderLines;
    }

    public PurchaseOrder(LocalDate orderDate, SellerId sellerId, String customerName) {
        this.orderDate = orderDate;
        this.purchaseOrderNumber = purchaseOrderNumber;
        this.sellerId = sellerId;
        CustomerName = customerName;
    }




    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public UUID getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public void setPurchaseOrderNumber(UUID purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
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

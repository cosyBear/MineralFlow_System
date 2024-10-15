package be.kdg.prog6.warehouseBoundedContext.domain;

import domain.MaterialType;

import java.time.LocalDate;
import java.util.UUID;


public class PurchaseOrder {
    // the date is when the order was placed.
    private LocalDate orderDate;

    private UUID purchaseOrderNumber;

    private SellerId sellerId;

    private String CustomerName;

    private MaterialType materialType;

    private double amountOfMaterialInTons;

    public PurchaseOrder(LocalDate orderDate, UUID purchaseOrderNumber, SellerId sellerId, String customerName, MaterialType materialType, double amountOfMaterialInTons) {
        this.orderDate = orderDate;
        this.purchaseOrderNumber = purchaseOrderNumber;
        this.sellerId = sellerId;
        CustomerName = customerName;
        this.materialType = materialType;
        this.amountOfMaterialInTons = amountOfMaterialInTons;
    }

    public PurchaseOrder(LocalDate orderDate, SellerId sellerId, String customerName, MaterialType materialType, double amountOfMaterialInTons) {
        this.orderDate = orderDate;
        this.sellerId = sellerId;
        CustomerName = customerName;
        this.materialType = materialType;
        this.amountOfMaterialInTons = amountOfMaterialInTons;
    }
    public PurchaseOrder(){}

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

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public double getAmountOfMaterialInTons() {
        return amountOfMaterialInTons;
    }

    public void setAmountOfMaterialInTons(double amountOfMaterialInTons) {
        this.amountOfMaterialInTons = amountOfMaterialInTons;
    }
}

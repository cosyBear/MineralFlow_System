package be.kdg.prog6.landSideBoundedContext.adapters.out.entity;


import domain.MaterialType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(catalog = "app_db")
public class PurchaseOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "purchase_order_id")
    private UUID purchaseOrderId;

    private LocalDate orderDate;

    private UUID sellerId;

    private String customerName;

    @Enumerated(EnumType.STRING)
    private MaterialType materialType;

    private double amountOfMaterialInTons;

    public PurchaseOrderEntity() {

    }
    public PurchaseOrderEntity(UUID purchaseOrderId, LocalDate orderDate, UUID sellerId, String customerName, MaterialType materialType, double amountOfMaterialInTons) {
        this.purchaseOrderId = purchaseOrderId;
        this.orderDate = orderDate;
        this.sellerId = sellerId;
        this.customerName = customerName;
        this.materialType = materialType;
        this.amountOfMaterialInTons = amountOfMaterialInTons;
    }


    public UUID getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(UUID purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public UUID getSellerId() {
        return sellerId;
    }

    public void setSellerId(UUID sellerId) {
        this.sellerId = sellerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

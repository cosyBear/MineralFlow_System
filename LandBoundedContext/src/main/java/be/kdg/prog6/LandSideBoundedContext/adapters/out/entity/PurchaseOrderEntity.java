package be.kdg.prog6.LandSideBoundedContext.adapters.out.entity;


import be.kdg.prog6.LandSideBoundedContext.domain.MaterialType;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class PurchaseOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer purchaseOrderId;

    private LocalDate orderDate;

    private String sellerId;

    private String customerName;

    @Enumerated(EnumType.STRING)
    private MaterialType materialType;

    private double amountOfMaterialInTons;

    // Constructors, Getters, and Setters

    public Integer getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Integer purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
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

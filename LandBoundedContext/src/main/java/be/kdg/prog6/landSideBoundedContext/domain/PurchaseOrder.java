package be.kdg.prog6.landSideBoundedContext.domain;

import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;

import java.time.LocalDate;

// purchase order is jsut id
// seller id // custome number,
//
// so the purchase order lines is about like each item in the cart
// like 10 tons of iron or 5 tons of
//
//purchase order lines means
// using the seller id  from the  appoitment we link the PurchaseOrder
//
// so the way we know how much we dump into the waerhouse is look at this shit class.
public class PurchaseOrder {
    // the date is when the order was placed.
    private LocalDate orderDate;

    private Integer purchaseOrderNumber;

    private SellerId sellerId;

    private String CustomerName;

    private MaterialType materialType;

    private double amountOfMaterialInTons;


    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public void setPurchaseOrderNumber(Integer purchaseOrderNumber) {
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

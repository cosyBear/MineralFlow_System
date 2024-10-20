package be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity;


import domain.MaterialType;
import jakarta.persistence.*;

import java.util.UUID;

@Table(catalog = "warehouse_db")
@Entity
public class PurchaseOrderLineEntity {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID orderLineId;

    @Enumerated(EnumType.STRING)
    private MaterialType materialType;

    private double quantity;

    private double pricePerTon;


    @ManyToOne
    private PurchaseOrderEntity purchaseOrder;


    public PurchaseOrderLineEntity() {

    }

    public PurchaseOrderEntity getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrderEntity purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public PurchaseOrderLineEntity(UUID orderLineId, MaterialType materialType, double quantity, double pricePerTon) {
        this.orderLineId = orderLineId;
        this.materialType = materialType;
        this.quantity = quantity;
        this.pricePerTon = pricePerTon;
    }

    public PurchaseOrderLineEntity( MaterialType materialType, double quantity, double pricePerTon) {
        this.materialType = materialType;
        this.quantity = quantity;
        this.pricePerTon = pricePerTon;
    }

    public double getPricePerTon() {
        return pricePerTon;
    }

    public void setPricePerTon(double pricePerTon) {
        this.pricePerTon = pricePerTon;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public UUID getOrderLineId() {
        return orderLineId;
    }

    public void setOrderLineId(UUID orderLineId) {
        this.orderLineId = orderLineId;
    }


}

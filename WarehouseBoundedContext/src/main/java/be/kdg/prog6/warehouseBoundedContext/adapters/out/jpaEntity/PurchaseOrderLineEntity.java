package be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity;


import domain.MaterialType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
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


}

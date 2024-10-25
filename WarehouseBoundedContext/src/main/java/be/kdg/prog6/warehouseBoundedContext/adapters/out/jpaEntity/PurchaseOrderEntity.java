package be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity;


import be.kdg.prog6.warehouseBoundedContext.domain.PurchaseOrderLine;
import domain.MaterialType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(catalog = "warehouse_db")
public class PurchaseOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "purchase_order_id")

    private UUID purchaseOrderId;

    private LocalDate orderDate;

    private UUID sellerId;

    private String customerName;

    @Enumerated(EnumType.STRING)
    private PurchaseOrderStatusEntity status;

    @OneToMany(mappedBy = "purchaseOrder",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseOrderLineEntity> purchaseOrderLines;

    private UUID buyerId;

    public PurchaseOrderEntity() {

    }
    public PurchaseOrderEntity(UUID purchaseOrderId, LocalDate orderDate, UUID sellerId, String customerName, List<PurchaseOrderLineEntity> purchaseOrderLines , PurchaseOrderStatusEntity status , UUID buyerId) {
        this.purchaseOrderId = purchaseOrderId;
        this.orderDate = orderDate;
        this.sellerId = sellerId;
        this.customerName = customerName;
        this.purchaseOrderLines = purchaseOrderLines;
        this.status = status;
        this.buyerId = buyerId;
    }
    public PurchaseOrderEntity(LocalDate orderDate, UUID sellerId, String customerName, List<PurchaseOrderLineEntity> purchaseOrderLines , PurchaseOrderStatusEntity status) {
        this.orderDate = orderDate;
        this.sellerId = sellerId;
        this.customerName = customerName;
        this.purchaseOrderLines = purchaseOrderLines;
        this.status = status;

    }

    public PurchaseOrderEntity(UUID purchaseOrderId, LocalDate orderDate, UUID sellerId, String customerName, PurchaseOrderStatusEntity status, List<PurchaseOrderLineEntity> purchaseOrderLines, UUID buyerId) {
        this.purchaseOrderId = purchaseOrderId;
        this.orderDate = orderDate;
        this.sellerId = sellerId;
        this.customerName = customerName;
        this.status = status;
        this.purchaseOrderLines = purchaseOrderLines;
        this.buyerId = buyerId;
    }

}

package be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity;


import be.kdg.prog6.warehouseBoundedContext.domain.PurchaseOrderLine;
import domain.MaterialType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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


    public PurchaseOrderEntity() {

    }
    public PurchaseOrderEntity(UUID purchaseOrderId, LocalDate orderDate, UUID sellerId, String customerName, List<PurchaseOrderLineEntity> purchaseOrderLines , PurchaseOrderStatusEntity status) {
        this.purchaseOrderId = purchaseOrderId;
        this.orderDate = orderDate;
        this.sellerId = sellerId;
        this.customerName = customerName;
        this.purchaseOrderLines = purchaseOrderLines;
        this.status = status;
    }
    public PurchaseOrderEntity(LocalDate orderDate, UUID sellerId, String customerName, List<PurchaseOrderLineEntity> purchaseOrderLines , PurchaseOrderStatusEntity status) {
        this.orderDate = orderDate;
        this.sellerId = sellerId;
        this.customerName = customerName;
        this.purchaseOrderLines = purchaseOrderLines;
        this.status = status;

    }

    public PurchaseOrderStatusEntity getStatus() {
        return status;
    }

    public void setStatus(PurchaseOrderStatusEntity status) {
        this.status = status;
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

    public List<PurchaseOrderLineEntity> getPurchaseOrderLines() {
        return purchaseOrderLines;
    }

    public void setPurchaseOrderLines(List<PurchaseOrderLineEntity> purchaseOrderLines) {
        this.purchaseOrderLines = purchaseOrderLines;
    }
}

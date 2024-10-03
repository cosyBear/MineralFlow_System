package be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity;
import be.kdg.prog6.warehouseBoundedContext.domain.*;
import jakarta.persistence.*;
import be.kdg.prog6.warehouseBoundedContext.domain.MaterialType;
import be.kdg.prog6.warehouseBoundedContext.domain.SellerId;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(  catalog = "warehouse_db")
public class WarehouseEntity {


    @Id
    @GeneratedValue
    private UUID warehouseNumber;

    @Enumerated(EnumType.STRING)
    private MaterialType materialType;

    @Embedded
    private SellerId sellerId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "events_window_id")
    private WarehouseEventsWindowEntity warehouseEventsWindowEntity;

    public WarehouseEntity() {}


    public WarehouseEntity(UUID warehouseNumber, MaterialType materialType, SellerId sellerId, WarehouseEventsWindowEntity warehouseEventsWindowEntity) {
        this.warehouseNumber = warehouseNumber;
        this.materialType = materialType;
        this.sellerId = sellerId;
        this.warehouseEventsWindowEntity = warehouseEventsWindowEntity;
    }

    public UUID getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(UUID warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public SellerId getSellerId() {
        return sellerId;
    }

    public void setSellerId(SellerId sellerId) {
        this.sellerId = sellerId;
    }

    public WarehouseEventsWindowEntity getWarehouseEventsWindowEntity() {
        return warehouseEventsWindowEntity;
    }

    public void setWarehouseEventsWindowEntity(WarehouseEventsWindowEntity warehouseEventsWindowEntity) {
        this.warehouseEventsWindowEntity = warehouseEventsWindowEntity;
    }
}




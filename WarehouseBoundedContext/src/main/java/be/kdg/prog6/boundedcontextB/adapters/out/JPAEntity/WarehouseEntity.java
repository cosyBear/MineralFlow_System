package be.kdg.prog6.boundedcontextB.adapters.out.JPAEntity;
import be.kdg.prog6.boundedcontextB.domain.*;
import jakarta.persistence.*;
import be.kdg.prog6.boundedcontextB.domain.MaterialType;
import be.kdg.prog6.boundedcontextB.domain.SellerId;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "warehouses" , catalog = "warehouse_db")
public class WarehouseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JdbcTypeCode(SqlTypes.CHAR)  // Store UUID as CHAR(36) in the database
    @Column(name ="warehouseID" )
    private WarehouseId warehouseNumber;

    @Enumerated(EnumType.STRING)
    private MaterialType materialType;

    @Embedded
    private SellerId sellerId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "events_window_id")
    private WarehouseEventsWindowEntity warehouseEventsWindowEntity;

    public WarehouseEntity() {}

    public WarehouseEntity(WarehouseEventsWindowEntity warehouseEventsWindowEntity) {
        this.warehouseEventsWindowEntity = warehouseEventsWindowEntity;
    }


    public WarehouseId getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(WarehouseId warehouseNumber) {
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




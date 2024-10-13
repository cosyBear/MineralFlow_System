package be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity;
import domain.MaterialType;
import jakarta.persistence.*;
import java.util.UUID;


@Entity
@Table(catalog = "warehouse_db")
public class WarehouseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID warehouseId;

    private UUID sellerId;


    @Enumerated(EnumType.STRING)
    private MaterialType materialType;

    // One Warehouse has one WarehouseEventsWindowEntity
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_events_window_id", referencedColumnName = "warehouseEventsWindowId")
    private WarehouseEventsWindowEntity warehouseEventsWindow;

    public WarehouseEntity() {
    }

    public WarehouseEntity(UUID warehouseId, UUID sellerId, MaterialType materialType, WarehouseEventsWindowEntity warehouseEventsWindow) {
        this.warehouseId = warehouseId;
        this.sellerId = sellerId;
        this.materialType = materialType;
        this.warehouseEventsWindow = warehouseEventsWindow;
    }

    public UUID getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(UUID warehouseId) {
        this.warehouseId = warehouseId;
    }

    public UUID getSellerId() {
        return sellerId;
    }

    public void setSellerId(UUID sellerId) {
        this.sellerId = sellerId;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public WarehouseEventsWindowEntity getWarehouseEventsWindow() {
        return warehouseEventsWindow;
    }


    public void setWarehouseEventsWindow(WarehouseEventsWindowEntity warehouseEventsWindow) {
        this.warehouseEventsWindow = warehouseEventsWindow;
    }
}



package be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity;
import jakarta.persistence.*;
import java.util.UUID;


@Entity
@Table(catalog = "warehouse_db")
public class WarehouseEntity {

    @Id
    private UUID warehouseId;

    private UUID sellerId;

    private String materialType;

    // One Warehouse has one WarehouseEventsWindowEntity
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_events_window_id", referencedColumnName = "warehouseEventsWindowId")
    private WarehouseEventsWindowEntity warehouseEventsWindow;

    public WarehouseEntity() {
    }

    public WarehouseEntity(UUID warehouseId, UUID sellerId, String materialType, WarehouseEventsWindowEntity warehouseEventsWindow) {
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

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public WarehouseEventsWindowEntity getWarehouseEventsWindow() {
        return warehouseEventsWindow;
    }


    public void setWarehouseEventsWindow(WarehouseEventsWindowEntity warehouseEventsWindow) {
        this.warehouseEventsWindow = warehouseEventsWindow;
    }
}



package be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(catalog = "warehouse_db")
public class WarehouseEventsWindowEntity {

    @Id
    private UUID warehouseEventsWindowId;

    private UUID warehouseId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "warehouseEventsWindow")
    private List<WarehouseEventEntity> warehouseEventList;

    public WarehouseEventsWindowEntity() {
        this.warehouseEventList = new ArrayList<>();

    }

    public WarehouseEventsWindowEntity(UUID warehouseEventsWindowId, UUID warehouseId, List<WarehouseEventEntity> warehouseEventList) {
        this.warehouseEventsWindowId = warehouseEventsWindowId;
        this.warehouseId = warehouseId;
        this.warehouseEventList = warehouseEventList;
    }

    public UUID getWarehouseEventsWindowId() {
        return warehouseEventsWindowId;
    }

    public void setWarehouseEventsWindowId(UUID warehouseEventsWindowId) {
        this.warehouseEventsWindowId = warehouseEventsWindowId;
    }

    public UUID getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(UUID warehouseId) {
        this.warehouseId = warehouseId;
    }

    public List<WarehouseEventEntity> getWarehouseEventList() {
        return warehouseEventList;
    }

    public void setWarehouseEventList(List<WarehouseEventEntity> eventList) {
        this.warehouseEventList = eventList;
    }
}

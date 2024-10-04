package be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;


@Entity
@Table(catalog = "warehouse_db")
public class WarehouseEventsWindowEntity {

    @Id
    private UUID warehouseEventsWindowId;

    private UUID warehouseId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_events_window_id")
    private List<WarehouseEventEntity> eventList;

    public WarehouseEventsWindowEntity() {
    }

    public WarehouseEventsWindowEntity(UUID warehouseEventsWindowId, UUID warehouseId, List<WarehouseEventEntity> eventList) {
        this.warehouseEventsWindowId = warehouseEventsWindowId;
        this.warehouseId = warehouseId;
        this.eventList = eventList;
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

    public List<WarehouseEventEntity> getEventList() {
        return eventList;
    }

    public void setEventList(List<WarehouseEventEntity> eventList) {
        this.eventList = eventList;
    }
}

package be.kdg.prog6.boundedcontextB.adapters.out.JPAEntity;


import be.kdg.prog6.boundedcontextB.domain.EventType;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "WarehouseEventsWindowEntity",catalog = "warehouse_db")

public class WarehouseEventsWindowEntity {

    @Id
    @JdbcTypeCode(SqlTypes.CHAR)  // Store UUID as CHAR(36) in the database
    @Column(name = "WarehouseEventWindowId")
    private UUID WarehouseEventWindowId;

    @JdbcTypeCode(SqlTypes.CHAR)  // Store UUID as CHAR(36) in the database
    @Column(name = "warehouseId")
    private UUID warehouseId;

    @OneToMany(mappedBy = "warehouseEventsWindow", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WarehouseEventEntity> warehouseEventList = new ArrayList<>();

    protected WarehouseEventsWindowEntity() {
    }

    public WarehouseEventsWindowEntity(UUID warehouseId, List<WarehouseEventEntity> warehouseEventList) {
        this.warehouseId = warehouseId;
        this.warehouseEventList = warehouseEventList;
    }

    public WarehouseEventsWindowEntity(UUID warehouseId) {
        this.warehouseId = warehouseId;
    }

    public void addEvent(WarehouseEventEntity event) {
        event.setWarehouseEventsWindow(this);  // Set bi-directional relationship
        warehouseEventList.add(event);
    }

    public List<WarehouseEventEntity> getWarehouseEventList() {
        return warehouseEventList;
    }


    public UUID getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(UUID warehouseId) {
        this.warehouseId = warehouseId;
    }

    public void setWarehouseEventList(List<WarehouseEventEntity> warehouseEventList) {
        this.warehouseEventList = warehouseEventList;
    }

    public UUID getWarehouseEventWindowId() {
        return WarehouseEventWindowId;
    }

    public void setWarehouseEventWindowId(UUID warehouseEventWindowId) {
        WarehouseEventWindowId = warehouseEventWindowId;
    }
}


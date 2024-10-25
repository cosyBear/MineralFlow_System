package be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Setter
@Getter
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

}

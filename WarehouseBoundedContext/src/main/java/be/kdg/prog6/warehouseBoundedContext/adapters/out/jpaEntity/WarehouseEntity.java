package be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity;

import domain.MaterialType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Setter
@Getter
@Entity
@Table(catalog = "warehouse_db")
public class WarehouseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID warehouseId;

    private UUID sellerId;


    @Enumerated(EnumType.STRING)
    private MaterialType materialType;

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


}



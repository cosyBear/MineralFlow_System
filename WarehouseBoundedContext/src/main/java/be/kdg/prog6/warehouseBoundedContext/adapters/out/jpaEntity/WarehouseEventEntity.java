package be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity;

import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseEventsWindow;
import domain.MaterialType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(catalog = "warehouse_db")
public class WarehouseEventEntity {

    @Id
    private UUID eventId;

    private LocalDateTime eventTime;

    private String eventType;

    private double materialWeight;


    private UUID weighBridgeTicketId;


    @Enumerated(EnumType.STRING)
    private MaterialType materialType;


    @ManyToOne
    @JoinColumn(name = "warehouse_events_window_id", nullable = false)
    private WarehouseEventsWindowEntity warehouseEventsWindow;

    public WarehouseEventEntity() {

    }

    public WarehouseEventEntity(UUID eventId, LocalDateTime eventTime, String eventType, double materialWeight, UUID weighBridgeTicketId, WarehouseEventsWindowEntity warehouseEventsWindow, MaterialType materialType) {
        this.eventId = eventId;
        this.eventTime = eventTime;
        this.eventType = eventType;
        this.materialWeight = materialWeight;
        this.weighBridgeTicketId = weighBridgeTicketId;
        this.warehouseEventsWindow = warehouseEventsWindow; // Set the relationship
        this.materialType = materialType;
    }

}

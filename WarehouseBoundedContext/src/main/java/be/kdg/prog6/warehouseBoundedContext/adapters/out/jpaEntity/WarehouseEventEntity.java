package be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseEventsWindow;
import domain.MaterialType;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
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

    public WarehouseEventEntity(UUID eventId, LocalDateTime eventTime, String eventType, double materialWeight, UUID weighBridgeTicketId, WarehouseEventsWindowEntity warehouseEventsWindow , MaterialType materialType) {
        this.eventId = eventId;
        this.eventTime = eventTime;
        this.eventType = eventType;
        this.materialWeight = materialWeight;
        this.weighBridgeTicketId = weighBridgeTicketId;
        this.warehouseEventsWindow = warehouseEventsWindow; // Set the relationship
        this.materialType = materialType;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public double getMaterialWeight() {
        return materialWeight;
    }

    public void setMaterialWeight(double materialWeight) {
        this.materialWeight = materialWeight;
    }

    public WarehouseEventsWindowEntity getWarehouseEventsWindow() {
        return warehouseEventsWindow;
    }

    public void setWarehouseEventsWindow(WarehouseEventsWindowEntity warehouseEventsWindow) {
        this.warehouseEventsWindow = warehouseEventsWindow;
    }

    public UUID getWeighBridgeTicketId() {
        return weighBridgeTicketId;
    }

    public void setWeighBridgeTicketId(UUID weighBridgeTicketId) {
        this.weighBridgeTicketId = weighBridgeTicketId;
    }
}

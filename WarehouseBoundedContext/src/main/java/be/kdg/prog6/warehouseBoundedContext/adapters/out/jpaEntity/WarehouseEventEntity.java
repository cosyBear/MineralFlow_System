package be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity;
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

    public WarehouseEventEntity() {
    }

    public WarehouseEventEntity(UUID eventId, LocalDateTime eventTime, String eventType, double materialWeight,  UUID weighBridgeTicketId) {
        this.eventId = eventId;
        this.eventTime = eventTime;
        this.eventType = eventType;
        this.materialWeight = materialWeight;
        this.weighBridgeTicketId = weighBridgeTicketId;
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



    public UUID getWeighBridgeTicketId() {
        return weighBridgeTicketId;
    }

    public void setWeighBridgeTicketId(UUID weighBridgeTicketId) {
        this.weighBridgeTicketId = weighBridgeTicketId;
    }
}

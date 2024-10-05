package be.kdg.prog6.warehouseBoundedContext.domain;


import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;


public final class WarehouseEvent {
    private  WarehouseEventId id;
    private  LocalDateTime time;
    private  EventType type;
    private  double materialWeight;
    private  UUID weighBridgeTicketId;
    private  UUID warehouseEventsWindowId;


    public WarehouseEvent(){

    }

    public WarehouseEvent(WarehouseEventId id, LocalDateTime time, EventType type, double materialWeight, UUID weighBridgeTicketId, UUID warehouseEventsWindowId) {
        this.id = id;
        this.time = time;
        this.type = type;
        this.materialWeight = materialWeight;
        this.weighBridgeTicketId = weighBridgeTicketId;
        this.warehouseEventsWindowId = warehouseEventsWindowId;
    }

    public double getChangeToLoad() {
        return type == EventType.DELIVER ? materialWeight : -materialWeight;
    }

    public WarehouseEventId id() {
        return id;
    }

    public LocalDateTime time() {
        return time;
    }

    public EventType type() {
        return type;
    }

    public double materialTrueWeight() {
        return materialWeight;
    }

    public UUID weighBridgeTicketId() {
        return weighBridgeTicketId;
    }

    public UUID warehouseEventsWindowId() {
        return warehouseEventsWindowId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (WarehouseEvent) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.time, that.time) &&
                Objects.equals(this.type, that.type) &&
                Double.doubleToLongBits(this.materialWeight) == Double.doubleToLongBits(that.materialWeight) &&
                Objects.equals(this.weighBridgeTicketId, that.weighBridgeTicketId) &&
                Objects.equals(this.warehouseEventsWindowId, that.warehouseEventsWindowId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time, type, materialWeight, weighBridgeTicketId, warehouseEventsWindowId);
    }

    @Override
    public String toString() {
        return "WarehouseEvent[" +
                "id=" + id + ", " +
                "time=" + time + ", " +
                "type=" + type + ", " +
                "endWeight=" + materialWeight + ", " +
                "weighBridgeTicketId=" + weighBridgeTicketId + ", " +
                "warehouseEventsWindowId=" + warehouseEventsWindowId + ']';
    }

}

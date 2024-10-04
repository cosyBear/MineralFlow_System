package be.kdg.prog6.warehouseBoundedContext.domain;


import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;


public record WarehouseEvent(WarehouseEventId id,
                             LocalDateTime time,
                             EventType type,
                             double materialTrueWeight,
                             UUID weighBridgeTicketId,
                             UUID warehouseEventsWindowId) {

    public WarehouseEvent {
        Objects.requireNonNull(id);
        Objects.requireNonNull(type);
        Objects.requireNonNull(weighBridgeTicketId);
        Objects.requireNonNull(warehouseEventsWindowId);
        Objects.requireNonNull(time);
    }

    public double getChangeToLoad() {
        return type == EventType.DELIVER ? materialTrueWeight : -materialTrueWeight;
    }
}

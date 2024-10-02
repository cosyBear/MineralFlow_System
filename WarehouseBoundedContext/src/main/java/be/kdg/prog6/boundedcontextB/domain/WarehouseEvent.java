package be.kdg.prog6.boundedcontextB.domain;


import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public record WarehouseEvent(WarehouseEventId id, LocalDateTime time, EventType type, double amount , UUID WeighBridgeTicketId) {
    public WarehouseEvent {
        Objects.requireNonNull(id);
        Objects.requireNonNull(type);
        Objects.requireNonNull(WeighBridgeTicketId);
        Objects.requireNonNull(time);
    }

    public double getChangeToLoad() {
        return type == EventType.DELIVER ? amount : -amount;
    }
}



package be.kdg.prog6.warehouseBoundedContext.domain;

import java.util.UUID;

public record WarehouseEventId(UUID id) {
    public WarehouseEventId() {
        this(UUID.randomUUID());  // Automatically generate a unique ID
    }
}
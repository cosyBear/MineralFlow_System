package be.kdg.prog6.warehouseBoundedContext.port.in;

import java.time.LocalDateTime;

public record WarehouseEventQuery(String materialType,
                                  LocalDateTime time,
                                  String eventType) {
}

package be.kdg.prog6.boundedcontextB.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class WarehouseEventsWindow {

    private UUID warehouseEventsWindowId;
    private WarehouseId warehouseId; 
    private List<WarehouseEvent> warehouseEventList;

    public WarehouseEventsWindow() {
        this.warehouseEventList = new ArrayList<>();
    }


    public void addEvent(WarehouseEvent event) {
        warehouseEventList.add(event);
    }

    public double calculateCurrentLoad() {
        return warehouseEventList.stream()
                .mapToDouble(WarehouseEvent::getChangeToLoad)
                .sum();
    }

    public List<WarehouseEvent> getWarehouseEventList() {
        return Collections.unmodifiableList(warehouseEventList);
    }

    public WarehouseId getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(WarehouseId warehouseId) {
        this.warehouseId = warehouseId;
    }

    public void setWarehouseEventList(List<WarehouseEvent> warehouseEventList) {
        this.warehouseEventList = warehouseEventList;
    }

    public UUID getWarehouseEventsWindowId() {
        return warehouseEventsWindowId;
    }

    public void setWarehouseEventsWindowId(UUID warehouseEventsWindowId) {
        this.warehouseEventsWindowId = warehouseEventsWindowId;
    }
}

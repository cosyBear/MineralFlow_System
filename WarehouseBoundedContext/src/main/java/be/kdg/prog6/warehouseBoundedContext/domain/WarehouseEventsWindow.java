package be.kdg.prog6.warehouseBoundedContext.domain;

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

    public void updateEventWithMaterial(UUID weighBridgeTicketId, double materialTrueWeight) {
        WarehouseEvent pendingEvent = findPendingEvent(weighBridgeTicketId);
        WarehouseEvent updatedEvent = new WarehouseEvent(
                pendingEvent.id(),
                pendingEvent.time(),
                pendingEvent.type(),
                materialTrueWeight,  // Updated material weight
                pendingEvent.WeighBridgeTicketId()
        );
        addEvent(updatedEvent);
    }


    public WarehouseEvent findPendingEvent(UUID weighBridgeTicketId) {
        return warehouseEventList.stream()
                .filter(event -> event.WeighBridgeTicketId().equals(weighBridgeTicketId))
                .filter(event -> event.materialTrueWeight() == 0)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No matching event found with weighBridgeTicketId: " + weighBridgeTicketId));
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

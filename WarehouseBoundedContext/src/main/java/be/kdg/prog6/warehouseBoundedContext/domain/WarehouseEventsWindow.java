package be.kdg.prog6.warehouseBoundedContext.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
//
//public class WarehouseEventsWindow {
//
//    private UUID warehouseEventsWindowId;
//    private WarehouseId warehouseId;
//    private List<WarehouseEvent> warehouseEventList;
//
//
//
//    public WarehouseEventsWindow() {
//        this.warehouseEventList = new ArrayList<>();
//        this.warehouseEventsWindowId = UUID.randomUUID();  // Ensure UUID is set on creation
//    }
//
//    public WarehouseEventsWindow(WarehouseId warehouseId, UUID warehouseEventsWindowId) {
//        this.warehouseId = warehouseId;
//        this.warehouseEventsWindowId = (warehouseEventsWindowId != null) ? warehouseEventsWindowId : UUID.randomUUID();
//        this.warehouseEventList = new ArrayList<>();
//    }
//
//
//
//    public void addEvent(WarehouseEvent event) {
//        warehouseEventList.add(event);
//    }
//
//    public double calculateCurrentLoad() {
//        return warehouseEventList.stream()
//                .mapToDouble(WarehouseEvent::getChangeToLoad)
//                .sum();
//    }
//    public void updateEventWithMaterial(UUID weighBridgeTicketId, double materialTrueWeight) {
//        WarehouseEvent pendingEvent = findPendingEvent(weighBridgeTicketId);
//        WarehouseEvent updatedEvent = new WarehouseEvent(
//                pendingEvent.id(),
//                pendingEvent.time(),
//                pendingEvent.type(),
//                materialTrueWeight,
//                weighBridgeTicketId,
//                this.warehouseEventsWindowId
//
//        );
//        addEvent(updatedEvent);
//    }
//
//
//    public WarehouseEvent findPendingEvent(UUID weighBridgeTicketId) {
//        return warehouseEventList.stream()
//                .filter(event -> event.weighBridgeTicketId().equals(weighBridgeTicketId))
//                .filter(event -> event.materialTrueWeight() == 0)
//                .findFirst()
//                .orElseThrow(() -> new IllegalStateException("No matching event found with weighBridgeTicketId: " + weighBridgeTicketId));
//    }
//
//    public List<WarehouseEvent> getWarehouseEventList() {
//        return Collections.unmodifiableList(warehouseEventList);
//    }
//
//    public WarehouseId getWarehouseId() {
//        return warehouseId;
//    }
//
//    public void setWarehouseId(WarehouseId warehouseId) {
//        this.warehouseId = warehouseId;
//    }
//
//    public void setWarehouseEventList(List<WarehouseEvent> warehouseEventList) {
//        this.warehouseEventList = warehouseEventList;
//    }
//
//    public UUID getWarehouseEventsWindowId() {
//        return warehouseEventsWindowId;
//    }
//
//    public void setWarehouseEventsWindowId(UUID warehouseEventsWindowId) {
//        this.warehouseEventsWindowId = warehouseEventsWindowId;
//    }
//}
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.time.LocalDateTime;

import java.time.LocalDateTime;
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
        this.warehouseEventsWindowId = UUID.randomUUID();
    }

    public WarehouseEventsWindow(WarehouseId warehouseId, UUID warehouseEventsWindowId) {
        this.warehouseId = warehouseId;
        this.warehouseEventsWindowId = (warehouseEventsWindowId != null) ? warehouseEventsWindowId : UUID.randomUUID();
        this.warehouseEventList = new ArrayList<>();
    }

    public void addEvent(WarehouseEvent event) {
        this.warehouseEventList.add(event);
    }

    public double getCurrentLoad() {
        return warehouseEventList.stream()
                .mapToDouble(WarehouseEvent::getChangeToLoad)
                .sum();
    }

    public void addMaterialWeightEvent(UUID weighBridgeTicketId, double materialTrueWeight) {
        WarehouseEvent newEvent = new WarehouseEvent(
                new WarehouseEventId(),
                LocalDateTime.now(),
                EventType.DELIVER,
                materialTrueWeight,
                weighBridgeTicketId,
                this.warehouseEventsWindowId
        );
        addEvent(newEvent);
    }

    // Getter and setter for warehouseEventList
    public List<WarehouseEvent> getWarehouseEventList() {
        return Collections.unmodifiableList(warehouseEventList);
    }

    public void setWarehouseEventList(List<WarehouseEvent> warehouseEventList) {
        this.warehouseEventList = warehouseEventList;
    }

    // Getter and setter for warehouseId
    public WarehouseId getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(WarehouseId warehouseId) {
        this.warehouseId = warehouseId;
    }

    // Getter and setter for warehouseEventsWindowId
    public UUID getWarehouseEventsWindowId() {
        return warehouseEventsWindowId;
    }

    public void setWarehouseEventsWindowId(UUID warehouseEventsWindowId) {
        this.warehouseEventsWindowId = warehouseEventsWindowId;
    }
}

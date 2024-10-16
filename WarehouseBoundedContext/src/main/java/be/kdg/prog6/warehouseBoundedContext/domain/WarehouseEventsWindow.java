package be.kdg.prog6.warehouseBoundedContext.domain;

import domain.MaterialType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.time.LocalDateTime;


public class WarehouseEventsWindow {

    private UUID warehouseEventsWindowId;
    private WarehouseId warehouseId;
    private List<WarehouseEvent> warehouseEventList;


    // TODO make a snap shot and also make a method to get the oldest mat
    public WarehouseEventsWindow() {
    }

    public WarehouseEventsWindow(WarehouseId warehouseId, UUID warehouseEventsWindowId , List<WarehouseEvent> warehouseEventList) {
        this.warehouseId = warehouseId;
        this.warehouseEventsWindowId = warehouseEventsWindowId;
        this.warehouseEventList = new ArrayList<>();
    }

    public WarehouseEventsWindow(WarehouseId warehouseId, UUID warehouseEventsWindowId) {
        this.warehouseId = warehouseId;
        this.warehouseEventsWindowId = warehouseEventsWindowId;
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

    public WarehouseEvent addMaterialWeightEvent(LocalDateTime weighOutTime , double amount  , UUID weighBridgeTicketId , MaterialType typeofMaterial) {
        WarehouseEvent newEvent = new WarehouseEvent(
                new WarehouseEventId(),
                weighOutTime,
                EventType.DELIVER,
                amount,
                weighBridgeTicketId,
                this.getWarehouseEventsWindowId(),
                typeofMaterial

        );
        addEvent(newEvent);
        return newEvent;
    }

    public List<WarehouseEvent> getWarehouseEventList() {
        return Collections.unmodifiableList(warehouseEventList);
    }

    public void setWarehouseEventList(List<WarehouseEvent> warehouseEventList) {
        this.warehouseEventList = warehouseEventList;
    }

    public WarehouseId getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(WarehouseId warehouseId) {
        this.warehouseId = warehouseId;
    }

    public UUID getWarehouseEventsWindowId() {
        return warehouseEventsWindowId;
    }

    public void setWarehouseEventsWindowId(UUID warehouseEventsWindowId) {
        this.warehouseEventsWindowId = warehouseEventsWindowId;
    }
}

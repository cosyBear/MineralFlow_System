package be.kdg.prog6.warehouseBoundedContext.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.time.LocalDateTime;


public class WarehouseEventsWindow {

    private UUID warehouseEventsWindowId;
    private WarehouseId warehouseId;
    private List<WarehouseEvent> warehouseEventList;

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

    public void addMaterialWeightEvent(WeighTruckOutCommand truckOutCommand) {
        WarehouseEvent newEvent = new WarehouseEvent(
                new WarehouseEventId(),
                truckOutCommand.getWeighOutTime(),
                EventType.DELIVER,
                truckOutCommand.getMaterialTrueWeight(),
                truckOutCommand.weighBridgeTicketId(),
                this.warehouseEventsWindowId
        );
        addEvent(newEvent);
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

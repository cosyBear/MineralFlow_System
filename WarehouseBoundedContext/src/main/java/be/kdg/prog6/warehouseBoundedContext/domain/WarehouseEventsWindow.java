package be.kdg.prog6.warehouseBoundedContext.domain;

import domain.MaterialType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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


    public void createSnapshot() {
        MaterialType materialType = warehouseEventList.get(0).getMaterialType();

        double totalAmount = warehouseEventList.stream().
                mapToDouble(WarehouseEvent::getChangeToLoad).sum();

        warehouseEventList.clear();

        warehouseEventList.add(
                new WarehouseEvent(
                        new WarehouseEventId(),
                        LocalDateTime.now(),
                        EventType.SNAPSHOT,
                        totalAmount,
                        this.getWarehouseEventsWindowId(),
                        materialType
                )
        );
    }
    //add extra attrubites like PO stataus to say if its done or not

    public List<WarehouseEvent> fulfillShippingOrder(double requiredAmount) {
        List<WarehouseEvent> shippingEvents = new ArrayList<>();
        double remainingAmount = requiredAmount;

        List<WarehouseEvent> sortedDeliverEvents = warehouseEventList.stream()
                .filter(event -> event.getType() == EventType.DELIVER)
                .sorted(Comparator.comparing(WarehouseEvent::getTime))
                .toList();

        double currentAvailableMaterial = getCurrentLoad();

        if (currentAvailableMaterial < requiredAmount) {
            throw new IllegalStateException("Not enough material available to fulfill the shipping order.");
        }
        for (WarehouseEvent deliverEvent : sortedDeliverEvents) {
            if (remainingAmount <= 0) {
                break;
            }
            double availableAmount = deliverEvent.getMaterialWeight();
            if (availableAmount <= remainingAmount) {
                shippingEvents.add(new WarehouseEvent(
                        new WarehouseEventId(),
                        LocalDateTime.now(),
                        EventType.SHIP,
                        availableAmount,
                        deliverEvent.getWeighBridgeTicketId(),
                        this.getWarehouseEventsWindowId(),
                        deliverEvent.getMaterialType()
                ));
                remainingAmount -= availableAmount;
            } else {
                shippingEvents.add(new WarehouseEvent(
                        new WarehouseEventId(),
                        LocalDateTime.now(),
                        EventType.SHIP,
                        remainingAmount,
                        deliverEvent.getWeighBridgeTicketId(),
                        this.getWarehouseEventsWindowId(),
                        deliverEvent.getMaterialType()
                ));
                remainingAmount = 0;
            }
        }

        // Step 3: Add new ship events to the event list (event sourcing)
        warehouseEventList.addAll(shippingEvents);

        return shippingEvents;
    }


    public WarehouseEventsWindow(WarehouseId warehouseId, UUID warehouseEventsWindowId, List<WarehouseEvent> warehouseEventList) {
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

    public WarehouseEvent addMaterialWeightEvent(LocalDateTime weighOutTime, double amount, UUID weighBridgeTicketId, MaterialType typeofMaterial) {
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

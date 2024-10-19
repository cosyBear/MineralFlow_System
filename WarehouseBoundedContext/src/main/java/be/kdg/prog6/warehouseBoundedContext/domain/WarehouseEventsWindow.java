package be.kdg.prog6.warehouseBoundedContext.domain;

import domain.MaterialType;

import java.util.*;
import java.time.LocalDateTime;


public class WarehouseEventsWindow {

    private UUID warehouseEventsWindowId;
    private WarehouseId warehouseId;
    private List<WarehouseEvent> warehouseEventList;


    // so when you ship enter the shipping order
    // we do a IO checks the ship order compare the ship order to the purchase order



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
    public List<WarehouseEvent> fulfillShippingOrder(Map<MaterialType, Double> requiredAmounts) {
        List<WarehouseEvent> shippingEvents = new ArrayList<>();

        for (Map.Entry<MaterialType, Double> entry : requiredAmounts.entrySet()) {
            MaterialType materialType = entry.getKey();
            double requiredAmount = entry.getValue();
            double remainingAmount = requiredAmount;

            List<WarehouseEvent> sortedDeliverEvents = warehouseEventList.stream()
                    .filter(event -> event.getType() == EventType.DELIVER && event.getMaterialType() == materialType)
                    .sorted(Comparator.comparing(WarehouseEvent::getTime))
                    .toList();

            double currentAvailableMaterial = sortedDeliverEvents.stream()
                    .mapToDouble(WarehouseEvent::getMaterialWeight)
                    .sum();

            if (currentAvailableMaterial < requiredAmount) {
                throw new IllegalStateException("Not enough material available to fulfill the shipping order for " + materialType);
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
                            -availableAmount,  // Subtracting material (negative)
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
                            -remainingAmount,
                            deliverEvent.getWeighBridgeTicketId(),
                            this.getWarehouseEventsWindowId(),
                            deliverEvent.getMaterialType()
                    ));
                    remainingAmount = 0;
                }
            }
        }

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

package be.kdg.prog6.warehouseBoundedContext.domain;

import domain.MaterialType;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.time.LocalDateTime;
import java.util.stream.Collectors;


@Setter
@Getter
public class WarehouseEventsWindow {

    private UUID warehouseEventsWindowId;
    private WarehouseId warehouseId;
    private List<WarehouseEvent> warehouseEventList = new ArrayList<>();

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

    public List<WarehouseEvent> fulfillShippingOrder(MaterialType materialType, double requiredAmount) {
        List<WarehouseEvent> shippingEvents = new ArrayList<>();
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

            double amountToShip = Math.min(availableAmount, remainingAmount);

            WarehouseEvent shipEvent = new WarehouseEvent(
                    new WarehouseEventId(),
                    LocalDateTime.now(),
                    EventType.SHIP,
                    amountToShip,
                    deliverEvent.getWeighBridgeTicketId(),
                    this.getWarehouseEventsWindowId(),
                    deliverEvent.getMaterialType()
            );
            shippingEvents.add(shipEvent);
            warehouseEventList.add(shipEvent);
            remainingAmount -= amountToShip;
        }

        return shippingEvents;
    }


    public WarehouseEventsWindow(WarehouseId warehouseId, UUID warehouseEventsWindowId, List<WarehouseEvent> warehouseEventList) {
        this.warehouseId = warehouseId;
        this.warehouseEventsWindowId = warehouseEventsWindowId;
        this.warehouseEventList = warehouseEventList;
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


}

package be.kdg.prog6.warehouseBoundedContext.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Warehouse {

    private WarehouseId warehouseNumber;
    private SellerId sellerId;
    private MaterialType materialType;
    private WarehouseEventsWindow eventsWindow;

    public Warehouse() {}

    public Warehouse(WarehouseId warehouseNumber, SellerId sellerId, MaterialType materialType) {

        this.warehouseNumber = warehouseNumber;
        this.sellerId = sellerId;
        this.materialType = materialType;
        this.eventsWindow = new WarehouseEventsWindow(this.warehouseNumber, UUID.randomUUID());
    }


    public void updateMaterialWeight(WeighTruckOutCommand truckOutCommand) {
        if (this.eventsWindow == null) {
            this.eventsWindow = new WarehouseEventsWindow(this.warehouseNumber, UUID.randomUUID());
        }

        eventsWindow.addMaterialWeightEvent( truckOutCommand);
    }

    public void beginDeliveryProcess(WeighTruckInCommand command) {
        if (this.eventsWindow == null) {
            this.eventsWindow = new WarehouseEventsWindow(this.warehouseNumber, UUID.randomUUID());
        }

        WarehouseEvent event = new WarehouseEvent(
                new WarehouseEventId(),
                command.weighInTime(),
                EventType.DELIVER,
                0,
                command.weighBridgeTicketId(),
                eventsWindow.getWarehouseEventsWindowId()
        );
        eventsWindow.addEvent(event);
    }

    // Getter and setter for warehouseNumber
    public WarehouseId getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(WarehouseId warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public SellerId getSellerId() {
        return sellerId;
    }

    public void setSellerId(SellerId sellerId) {
        this.sellerId = sellerId;
    }

    public WarehouseEventsWindow getEventsWindow() {
        return eventsWindow;
    }

    public void setEventsWindow(WarehouseEventsWindow eventsWindow) {
        this.eventsWindow = eventsWindow;
    }

    public double getCurrentLoadOfWarehouse() {
        return eventsWindow.getCurrentLoad();
    }
}

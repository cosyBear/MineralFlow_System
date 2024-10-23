package be.kdg.prog6.warehouseBoundedContext.domain;

import domain.MaterialType;

import java.time.LocalDateTime;
import java.util.List;
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


    public WarehouseEvent deliveryMaterial(LocalDateTime weighOutTime , double amount  , UUID weighBridgeTicketId , MaterialType typeofMaterial) {
        if (this.eventsWindow == null) {
            this.eventsWindow = new WarehouseEventsWindow(this.warehouseNumber, UUID.randomUUID());
        }
        return eventsWindow.addMaterialWeightEvent( weighOutTime , amount, weighBridgeTicketId ,typeofMaterial );
    }

    public List<WarehouseEvent> fulfillShippingOrder(MaterialType materialType, double requiredAmount) {
        return eventsWindow.fulfillShippingOrder(materialType, requiredAmount);
    }

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

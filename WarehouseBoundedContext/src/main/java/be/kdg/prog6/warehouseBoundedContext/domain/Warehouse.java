package be.kdg.prog6.warehouseBoundedContext.domain;

import domain.MaterialType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class Warehouse {

    private WarehouseId warehouseNumber;
    private SellerId sellerId;
    private MaterialType materialType;
    private WarehouseEventsWindow eventsWindow;

    public Warehouse() {
    }

    public Warehouse(WarehouseId warehouseNumber, SellerId sellerId, MaterialType materialType) {

        this.warehouseNumber = warehouseNumber;
        this.sellerId = sellerId;
        this.materialType = materialType;
        this.eventsWindow = new WarehouseEventsWindow(this.warehouseNumber, UUID.randomUUID());
    }


    public WarehouseEvent deliveryMaterial(LocalDateTime weighOutTime, double amount, UUID weighBridgeTicketId, MaterialType typeofMaterial) {
        if (this.eventsWindow == null) {
            this.eventsWindow = new WarehouseEventsWindow(this.warehouseNumber, UUID.randomUUID());
        }
        return eventsWindow.addMaterialWeightEvent(weighOutTime, amount, weighBridgeTicketId, typeofMaterial);
    }

    public List<WarehouseEvent> fulfillShippingOrder(MaterialType materialType, double requiredAmount) {
        return eventsWindow.fulfillShippingOrder(materialType, requiredAmount);
    }

    public double getCurrentLoadOfWarehouse() {
        return eventsWindow.getCurrentLoad();
    }
}

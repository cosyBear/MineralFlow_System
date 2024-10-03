package be.kdg.prog6.warehouseBoundedContext.domain;

import java.time.LocalDateTime;
import java.util.UUID;

//● As a truck driver, I want to receive the weighing bridge number when accessing the site so I
//know where to go. ask the teacher for this user story tell him in my case i dont have the weighing bridge i just a weighing bridgeUserCase. no weighing bridge domain
// and the way i assign the Truck a warehouse si when the truck goes in aKA in the WareHouseBounded context in the WarehouseUseCase
// so is that ok like the logic that  i imp follows the the doc only the part about weighing bridge should assing a warehouse
public class Warehouse {


    private WarehouseId warehouseNumber;


    private SellerId sellerId;

    private MaterialType materialType;

    private WarehouseEventsWindow eventsWindow;
    //create a SnapShot of the WareHouseEvent or the WarehouseEventsWindow for the calculating so its mush faster instead of replaying everything you have a snapshot

    public Warehouse() {

    }


    public Warehouse(WarehouseId warehouseNumber , SellerId sellerId, MaterialType materialType) {
        this.warehouseNumber = warehouseNumber;
        this.sellerId = sellerId;
        this.materialType = materialType;
        this.eventsWindow = new WarehouseEventsWindow();
        this.eventsWindow.setWarehouseId(warehouseNumber);  // Set warehouseId here
    }

    public void updateMaterialWeight(UUID weighBridgeTicketId, double materialTrueWeight) {
        eventsWindow.updateEventWithMaterial(weighBridgeTicketId, materialTrueWeight);
    }

    public void beginDeliveryProcess(weighTruckCommand command) {
        if (this.eventsWindow == null) {
            this.eventsWindow = new WarehouseEventsWindow(); // Initialize here
        }

        WarehouseEvent event = new WarehouseEvent(new WarehouseEventId(), LocalDateTime.now(), EventType.DELIVER, 0, command.weighBridgeTicketId());
        eventsWindow.addEvent(event);
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

    public double calculateCurrentLoadOfWarehouse() {
        return eventsWindow.calculateCurrentLoad();
    }

}



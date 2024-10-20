package be.kdg.prog6.watersideboundedcontext.domain;

import be.kdg.prog6.watersideboundedcontext.util.InspectionOperationException;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ShipmentOrder {

    private UUID purchaseOrder;
    private UUID vesselNumber;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private BunkeringOperation bunkeringOperation;
    private InspectionOperation inspectionOperation;


    public ShipmentOrder(InspectionOperation inspectionOperation, BunkeringOperation bunkeringOperation, LocalDateTime departureTime, LocalDateTime arrivalTime, UUID vesselNumber, UUID purchaseOrder) {
        this.inspectionOperation = inspectionOperation;
        this.bunkeringOperation = bunkeringOperation;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.vesselNumber = vesselNumber;
        this.purchaseOrder = purchaseOrder;
    }

    public ShipmentOrder(UUID purchaseOrder, UUID vesselNumber, LocalDateTime arrivalTime, LocalDateTime departureTime) {
        this.purchaseOrder = purchaseOrder;
        this.vesselNumber = vesselNumber;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public void performInspectionOperation(UUID purchaseOrderId) {
        if (this.purchaseOrder.equals(purchaseOrderId)) {
            this.inspectionOperation =  new InspectionOperation(LocalDateTime.now(), "Signed by pookie");
        } else {
            throw new InspectionOperationException("The purchaseOrderId does not match the purchaseOrderId in the shipmentOrder");
        }
    }

    public void  completeBunkeringOperation(LocalDateTime date) {
        this.bunkeringOperation = new BunkeringOperation(date);
    }


    public boolean canShipLeave(){
        return this.bunkeringOperation != null && this.inspectionOperation != null;

    }

}

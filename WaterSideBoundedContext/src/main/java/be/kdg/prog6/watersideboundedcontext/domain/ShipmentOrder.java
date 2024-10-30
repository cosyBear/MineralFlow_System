package be.kdg.prog6.watersideboundedcontext.domain;

import org.springframework.cglib.core.Local;
import util.errorClasses.InspectionOperationException;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShipmentOrder {
    private UUID shipmentOrderId;
    private UUID purchaseOrder;
    private UUID vesselNumber;
    private LocalDateTime expectedArrivalTime;
    private LocalDateTime expectedDepartureTime;
    private LocalDateTime actualArrivalTime;
    private LocalDateTime actualDepartureTime;
    private BunkeringOperation bunkeringOperation;
    private InspectionOperation inspectionOperation;
    private ShipmentOrderStatus status;

    public ShipmentOrder(InspectionOperation inspectionOperation, BunkeringOperation bunkeringOperation, LocalDateTime departureTime,
                         LocalDateTime arrivalTime, UUID vesselNumber, UUID purchaseOrder, UUID shipmentOrderId,
                         LocalDateTime actualArrivalTime, LocalDateTime actualDepartureTime, ShipmentOrderStatus status) {
        this.inspectionOperation = inspectionOperation;
        this.bunkeringOperation = bunkeringOperation;
        this.expectedDepartureTime = departureTime;
        this.expectedArrivalTime = arrivalTime;
        this.vesselNumber = vesselNumber;
        this.purchaseOrder = purchaseOrder;
        this.shipmentOrderId = shipmentOrderId;
        this.actualArrivalTime = actualArrivalTime;
        this.actualDepartureTime = actualDepartureTime;
        this.status = status;
    }

    public ShipmentOrder(UUID purchaseOrder, UUID vesselNumber, LocalDateTime arrivalTime, LocalDateTime departureTime) {
        this.purchaseOrder = purchaseOrder;
        this.vesselNumber = vesselNumber;
        this.expectedArrivalTime = arrivalTime;
        this.expectedDepartureTime = departureTime;
    }

    public void updateShipStatus(ShipmentOrderStatus newStatus) {
        this.status = newStatus;
    }


    public String performBunkeringOperation(LocalDateTime bunkeringTime) {
        this.bunkeringOperation = new BunkeringOperation(bunkeringTime);
        return "the bunkeringOperation is successful ";

    }

    public boolean isShipmentOrderCompleted(){
        return this.status.equals(ShipmentOrderStatus.COMPLETED);
    }

    public void performInspectionOperation(LocalDateTime timeOfSigning, String signature) {
        this.inspectionOperation = new InspectionOperation(timeOfSigning, signature);
    }


    public void insertActualArrivalTime(LocalDateTime actualArrivalTime) {
        this.actualArrivalTime = actualArrivalTime;
    }

    public void insertActualDepartureTime(LocalDateTime actualDepartureTime) {
        this.actualDepartureTime = actualDepartureTime;
    }

    public boolean isShipAllowedToLeave() {
        return bunkeringOperation.isCompleted() && inspectionOperation.isCompleted();
    }

}

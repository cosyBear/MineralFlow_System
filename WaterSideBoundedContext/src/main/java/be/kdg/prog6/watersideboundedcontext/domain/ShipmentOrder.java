package be.kdg.prog6.watersideboundedcontext.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class ShipmentOrder {

    private UUID purchaseOrder;
    private Vessel vesselNumber;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;


    public ShipmentOrder(UUID purchaseOrder, Vessel vesselNumber, LocalDateTime arrivalTime, LocalDateTime departureTime) {
        this.purchaseOrder = purchaseOrder;
        this.vesselNumber = vesselNumber;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }



    public UUID getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(UUID purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public Vessel getVesselNumber() {
        return vesselNumber;
    }

    public void setVesselNumber(Vessel vesselNumber) {
        this.vesselNumber = vesselNumber;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }
}

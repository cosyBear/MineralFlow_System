package be.kdg.prog6.boundedcontextB.domain;

import java.time.LocalDateTime;

public class PDT {

    // TODO1 A METHOD IN HERE that create an event for the PDT.The PDT registers the type of material and the time of delivery.The PDT registers the type of material and the time of delivery.

    private MaterialType materialType;
    private LocalDateTime deliveryTime;
    private double Payload;


    public PDT(MaterialType materialType, LocalDateTime deliveryTime, double payload) {
        this.materialType = materialType;
        this.deliveryTime = deliveryTime;
        this.Payload = payload;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public double getPayload() {
        return Payload;
    }

    public void setPayload(double payload) {
        Payload = payload;
    }
}

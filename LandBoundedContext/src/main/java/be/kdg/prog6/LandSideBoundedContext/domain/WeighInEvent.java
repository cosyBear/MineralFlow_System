package be.kdg.prog6.LandSideBoundedContext.domain;

import java.time.LocalDateTime;

public class WeighInEvent {

    private WeighBridgeTicketId weighBridgeTicketId; // chane this to UUID and change the name to the full name

    private LicensePlate licensePlate;

    private SellerId sellerId;
    private double Weight;

    private MaterialType materialType;
    private LocalDateTime weighInTime;


    public WeighInEvent(WeighBridgeTicketId weighBridgeTicketId, LicensePlate licensePlate, SellerId sellerId, double weight, MaterialType materialType, LocalDateTime weighInTime) {

        this.weighInTime = weighInTime;
        this.materialType = materialType;
        this.Weight = weight;
        this.sellerId = sellerId;
        this.licensePlate = licensePlate;
        this.weighBridgeTicketId = weighBridgeTicketId;
    }

    public WeighBridgeTicketId getWeighBridgeTicketId() {
        return weighBridgeTicketId;
    }

    public void setWeighBridgeTicketId(WeighBridgeTicketId weighBridgeTicketId) {
        this.weighBridgeTicketId = weighBridgeTicketId;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(LicensePlate licensePlate) {
        this.licensePlate = licensePlate;
    }

    public SellerId getSellerId() {
        return sellerId;
    }

    public void setSellerId(SellerId sellerId) {
        this.sellerId = sellerId;
    }

    public double getWeight() {
        return Weight;
    }

    public void setWeight(double weight) {
        Weight = weight;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public LocalDateTime getWeighInTime() {
        return weighInTime;
    }

    public void setWeighInTime(LocalDateTime weighInTime) {
        this.weighInTime = weighInTime;
    }
}

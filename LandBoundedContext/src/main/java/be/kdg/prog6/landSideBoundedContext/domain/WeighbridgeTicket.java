package be.kdg.prog6.landSideBoundedContext.domain;

import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import be.kdg.prog6.landSideBoundedContext.domain.Id.WeighBridgeTicketId;
import domain.MaterialType;

import java.time.LocalDateTime;

public class WeighbridgeTicket {

    private WeighBridgeTicketId weighBridgeTicketId;

    private LicensePlate licensePlate;

    private SellerId sellerId;

    private double startWeight;// total 100

    private double endWeight;// 50

    private LocalDateTime endTime; // truck out

    private LocalDateTime startTime;// truck in

    private MaterialType materialType;

    public WeighbridgeTicket(){

    }

    public WeighbridgeTicket(WeighBridgeTicketId weighBridgeTicketId, LicensePlate licensePlate, SellerId sellerId, double startWeight, double endWeight, MaterialType materialType, LocalDateTime startTime) {

        this.weighBridgeTicketId = weighBridgeTicketId;
        this.licensePlate = licensePlate;
        this.sellerId = sellerId;
        this.startWeight = startWeight;
        this.endWeight = endWeight;
        this.materialType = materialType;
        this.startTime = startTime;

    }

    public WeighBridgeTicketId getWeighBridgeTicketId() {
        return weighBridgeTicketId;
    }

    public void setWeighBridgeTicketId(WeighBridgeTicketId weighBridgeTicketId) {
        this.weighBridgeTicketId = weighBridgeTicketId;
    }

    public void truckWeighsOut(LocalDateTime endTime , double endWeight){
        this.endTime = endTime;
        this.endWeight = endWeight;
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

    public double getStartWeight() {
        return startWeight;
    }

    public void setStartWeight(double startWeight) {
        this.startWeight = startWeight;
    }

    public double getEndWeight() {
        return endWeight;
    }

    public void setEndWeight(double endWeight) {
        this.endWeight = endWeight;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}

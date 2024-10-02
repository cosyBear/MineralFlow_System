package be.kdg.prog6.LandSideBoundedContext.adapters.out.entity;


import be.kdg.prog6.LandSideBoundedContext.domain.MaterialType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(catalog = "app_db")
public class WeighbridgeTicketEntity {

    @Id
    private UUID weighBridgeTicketId;

    private String  licensePlate;

    private UUID sellerId;

    private double startWeight;

    private double endWeight;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private MaterialType materialType;


    public WeighbridgeTicketEntity(){

    }
    public WeighbridgeTicketEntity(UUID weighBridgeTicketId, String licensePlate, UUID sellerId, double startWeight, double endWeight, LocalDateTime endTime, MaterialType materialType, LocalDateTime startTime) {
        this.weighBridgeTicketId = weighBridgeTicketId;
        this.licensePlate = licensePlate;
        this.sellerId = sellerId;
        this.startWeight = startWeight;
        this.endWeight = endWeight;
        this.endTime = endTime;
        this.materialType = materialType;
        this.startTime = startTime;
    }

    public UUID getWeighBridgeTicketId() {
        return weighBridgeTicketId;
    }

    public void setWeighBridgeTicketId(UUID weighBridgeTicketId) {
        this.weighBridgeTicketId = weighBridgeTicketId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public UUID getSellerId() {
        return sellerId;
    }

    public void setSellerId(UUID sellerId) {
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

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
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
}

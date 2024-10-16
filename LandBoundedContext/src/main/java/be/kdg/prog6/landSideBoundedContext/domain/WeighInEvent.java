package be.kdg.prog6.landSideBoundedContext.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import domain.MaterialType;

import java.time.LocalDateTime;
import java.util.UUID;

public class WeighInEvent {

    private UUID weighBridgeTicketId;

    private String licensePlate;

    private UUID sellerId;


    @JsonProperty("grossWeight")
    private double grossWeight;// 100 tons truck pls materiuals

    private MaterialType materialType;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonProperty("weighInTime")
    private LocalDateTime weighInTime;


    public WeighInEvent(UUID weighBridgeTicketId, String licensePlate, UUID sellerId, double grossWeight, MaterialType materialType, LocalDateTime weighInTime) {
        this.weighBridgeTicketId = weighBridgeTicketId;
        this.licensePlate = licensePlate;
        this.sellerId = sellerId;
        this.grossWeight = grossWeight;
        this.materialType = materialType;
        this.weighInTime = weighInTime;
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

    public double getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(double grossWeight) {
        this.grossWeight = grossWeight;
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

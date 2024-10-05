package be.kdg.prog6.landSideBoundedContext.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.UUID;

public class WeighOutEvent {
    private UUID weighBridgeTicketId;

    private String licensePlate;

    private UUID sellerId;
    private double EndWeight;

    private MaterialType materialType;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime weighOutTime;

    private WarehouseStatus warehouseStatus;


    public WeighOutEvent(UUID weighBridgeTicketId, String licensePlate, UUID sellerId, double endWeight, MaterialType materialType, LocalDateTime weighOutTime, WarehouseStatus warehouseStatus) {
        this.weighBridgeTicketId = weighBridgeTicketId;
        this.licensePlate = licensePlate;
        this.sellerId = sellerId;
        this.EndWeight = endWeight;
        this.materialType = materialType;
        this.weighOutTime = weighOutTime;
        this.warehouseStatus = warehouseStatus;
    }




    public UUID getWeighBridgeTicketId() {
        return weighBridgeTicketId;
    }

    public void setWeighBridgeTicketId(UUID weighBridgeTicketId) {
        this.weighBridgeTicketId = weighBridgeTicketId;
    }

    public WarehouseStatus getWarehouseStatus() {
        return warehouseStatus;
    }

    public void setWarehouseStatus(WarehouseStatus warehouseStatus) {
        this.warehouseStatus = warehouseStatus;
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

    public double getEndWeight() {
        return EndWeight;
    }

    public void setEndWeight(double endWeight) {
        EndWeight = endWeight;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public LocalDateTime getWeighOutTime() {
        return weighOutTime;
    }

    public void setWeighOutTime(LocalDateTime weighOutTime) {
        this.weighOutTime = weighOutTime;
    }
}

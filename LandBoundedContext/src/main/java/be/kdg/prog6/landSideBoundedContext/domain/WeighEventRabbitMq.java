package be.kdg.prog6.landSideBoundedContext.domain;

import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import domain.MaterialType;

import java.time.LocalDateTime;
import java.util.UUID;

public class WeighEventRabbitMq {

    private UUID weighBridgeTicketId; // chane this to UUID and change the name to the full name

    private LicensePlate licensePlate;
    private SellerId sellerId;
    private double Weight;

    private MaterialType materialType;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime weighInTime;

    private WarehouseStatus warehouseStatus;


    public WeighEventRabbitMq(UUID weighBridgeTicketId, LicensePlate licensePlate, SellerId sellerId, double weight, MaterialType materialType, LocalDateTime weighInTime, WarehouseStatus warehouseStatus) {
        this.weighBridgeTicketId = weighBridgeTicketId;
        this.licensePlate = licensePlate;
        this.sellerId = sellerId;
        this.Weight = weight;
        this.materialType = materialType;
        this.weighInTime = weighInTime;
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

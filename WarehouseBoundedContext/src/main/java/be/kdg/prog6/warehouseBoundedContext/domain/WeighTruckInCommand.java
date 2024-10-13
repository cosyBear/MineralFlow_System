package be.kdg.prog6.warehouseBoundedContext.domain;

import domain.MaterialType;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public final class WeighTruckInCommand {
    private final UUID weighBridgeTicketId;
    private final String licensePlate;
    private final SellerId sellerId;
    private final double grossWeight;
    private final MaterialType materialType;
    private final LocalDateTime weighInTime;
    private final String wareHouseStatus;

    public WeighTruckInCommand(UUID weighBridgeTicketId, String licensePlate, SellerId sellerId, double grossWeight, MaterialType materialType,
                               LocalDateTime weighInTime, String wareHouseStatus) {
        this.weighBridgeTicketId = weighBridgeTicketId;
        this.licensePlate = licensePlate;
        this.sellerId = sellerId;
        this.grossWeight = grossWeight;
        this.materialType = materialType;
        this.weighInTime = weighInTime;
        this.wareHouseStatus = wareHouseStatus;
    }

    public UUID weighBridgeTicketId() {
        return weighBridgeTicketId;
    }

    public UUID getWeighBridgeTicketId() {
        return weighBridgeTicketId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public SellerId getSellerId() {
        return sellerId;
    }

    public double getGrossWeight() {
        return grossWeight;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public LocalDateTime getWeighInTime() {
        return weighInTime;
    }

    public String getWareHouseStatus() {
        return wareHouseStatus;
    }

    public String licensePlate() {
        return licensePlate;
    }

    public SellerId sellerId() {
        return sellerId;
    }

    public double grossWeight() {
        return grossWeight;
    }

    public MaterialType materialType() {
        return materialType;
    }

    public LocalDateTime weighInTime() {
        return weighInTime;
    }

    public String wareHouseStatus() {
        return wareHouseStatus;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (WeighTruckInCommand) obj;
        return Objects.equals(this.weighBridgeTicketId, that.weighBridgeTicketId) &&
                Objects.equals(this.licensePlate, that.licensePlate) &&
                Objects.equals(this.sellerId, that.sellerId) &&
                Double.doubleToLongBits(this.grossWeight) == Double.doubleToLongBits(that.grossWeight) &&
                Objects.equals(this.materialType, that.materialType) &&
                Objects.equals(this.weighInTime, that.weighInTime) &&
                Objects.equals(this.wareHouseStatus, that.wareHouseStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weighBridgeTicketId, licensePlate, sellerId, grossWeight, materialType, weighInTime, wareHouseStatus);
    }

    @Override
    public String toString() {
        return "WeighTruckCommand[" +
                "weighBridgeTicketId=" + weighBridgeTicketId + ", " +
                "licensePlate=" + licensePlate + ", " +
                "sellerId=" + sellerId + ", " +
                "weighInTime=" + grossWeight + ", " +
                "materialType=" + materialType + ", " +
                "weighInTime=" + weighInTime + ", " +
                "wareHouseStatus=" + wareHouseStatus + ']';
    }


}

package be.kdg.prog6.warehouseBoundedContext.domain;

import domain.MaterialType;

import java.time.LocalDateTime;
import java.util.UUID;

public class WeighTruckOutCommand {

    private final UUID weighBridgeTicketId;
    private final String licensePlate;
    private final SellerId sellerId;
    private final double materialTrueWeight;
    private final MaterialType materialType;
    private final LocalDateTime weighOutTime;
    private final String wareHouseStatus;

    public WeighTruckOutCommand(UUID weighBridgeTicketId, String licensePlate, SellerId sellerId, double materialTrueWeight, MaterialType materialType,
                                LocalDateTime weighOutTime, String wareHouseStatus) {
        this.weighBridgeTicketId = weighBridgeTicketId;
        this.licensePlate = licensePlate;
        this.sellerId = sellerId;
        this.materialTrueWeight = materialTrueWeight;
        this.materialType = materialType;
        this.weighOutTime = weighOutTime;
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

    public double getMaterialTrueWeight() {
        return materialTrueWeight;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public LocalDateTime getWeighOutTime() {
        return weighOutTime;
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
        return materialTrueWeight;
    }

    public MaterialType materialType() {
        return materialType;
    }

    public LocalDateTime weighInTime() {
        return weighOutTime;
    }

    public String wareHouseStatus() {
        return wareHouseStatus;
    }


    @Override
    public String toString() {
        return "WeighTruckCommand[" +
                "weighBridgeTicketId=" + weighBridgeTicketId + ", " +
                "licensePlate=" + licensePlate + ", " +
                "sellerId=" + sellerId + ", " +
                "weighInTime=" + materialTrueWeight + ", " +
                "materialType=" + materialType + ", " +
                "weighInTime=" + weighOutTime + ", " +
                "wareHouseStatus=" + wareHouseStatus + ']';
    }

}

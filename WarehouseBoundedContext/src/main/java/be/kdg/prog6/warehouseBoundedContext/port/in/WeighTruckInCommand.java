package be.kdg.prog6.warehouseBoundedContext.port.in;

import be.kdg.prog6.warehouseBoundedContext.domain.SellerId;
import domain.MaterialType;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public final class WeighTruckInCommand {
    private final UUID weighBridgeTicketId;
    private final String licensePlate;
    private final SellerId sellerId;
    private final double grossWeight;
    private final MaterialType materialType;
    private final LocalDateTime weighInTime;

    public WeighTruckInCommand(UUID weighBridgeTicketId, String licensePlate, SellerId sellerId, double grossWeight, MaterialType materialType,
                               LocalDateTime weighInTime) {
        this.weighBridgeTicketId = weighBridgeTicketId;
        this.licensePlate = licensePlate;
        this.sellerId = sellerId;
        this.grossWeight = grossWeight;
        this.materialType = materialType;
        this.weighInTime = weighInTime;
    }

    public UUID weighBridgeTicketId() {
        return weighBridgeTicketId;
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



    @Override
    public String toString() {
        return "WeighTruckCommand[" +
                "weighBridgeTicketId=" + weighBridgeTicketId + ", " +
                "licensePlate=" + licensePlate + ", " +
                "sellerId=" + sellerId + ", " +
                "weighInTime=" + grossWeight + ", " +
                "materialType=" + materialType + ", " +
                "weighInTime=" + weighInTime + ", ";

    }


}

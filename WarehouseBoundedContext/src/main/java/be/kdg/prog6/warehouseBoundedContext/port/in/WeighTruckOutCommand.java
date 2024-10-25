package be.kdg.prog6.warehouseBoundedContext.port.in;

import be.kdg.prog6.warehouseBoundedContext.domain.SellerId;
import domain.MaterialType;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class WeighTruckOutCommand {

    private final UUID weighBridgeTicketId;
    private final String licensePlate;
    private final SellerId sellerId;
    private final double materialTrueWeight;
    private final MaterialType materialType;
    private final LocalDateTime weighOutTime;

    public WeighTruckOutCommand(UUID weighBridgeTicketId, String licensePlate, SellerId sellerId, double materialTrueWeight, MaterialType materialType,
                                LocalDateTime weighOutTime) {
        this.weighBridgeTicketId = weighBridgeTicketId;
        this.licensePlate = licensePlate;
        this.sellerId = sellerId;
        this.materialTrueWeight = materialTrueWeight;
        this.materialType = materialType;
        this.weighOutTime = weighOutTime;
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
        return materialTrueWeight;
    }

    public MaterialType materialType() {
        return materialType;
    }

    public LocalDateTime weighInTime() {
        return weighOutTime;
    }



    @Override
    public String toString() {
        return "WeighTruckCommand[" +
                "weighBridgeTicketId=" + weighBridgeTicketId + ", " +
                "licensePlate=" + licensePlate + ", " +
                "sellerId=" + sellerId + ", " +
                "weighInTime=" + materialTrueWeight + ", " +
                "materialType=" + materialType + ", " +
                "weighInTime=" + weighOutTime + ", ";
    }

}

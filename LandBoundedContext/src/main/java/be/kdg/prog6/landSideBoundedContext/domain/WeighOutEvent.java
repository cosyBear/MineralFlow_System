package be.kdg.prog6.landSideBoundedContext.domain;

import domain.MaterialType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
public class WeighOutEvent {
    private UUID weighBridgeTicketId;

    private String licensePlate;

    private UUID sellerId;
    private double EndWeight; // 50

    private MaterialType materialType;
    private LocalDateTime weighOutTime;



    public WeighOutEvent(UUID weighBridgeTicketId, String licensePlate, UUID sellerId, double endWeight, MaterialType materialType, LocalDateTime weighOutTime) {
        this.weighBridgeTicketId = weighBridgeTicketId;
        this.licensePlate = licensePlate;
        this.sellerId = sellerId;
        this.EndWeight = endWeight;
        this.materialType = materialType;
        this.weighOutTime = weighOutTime;
    }


}

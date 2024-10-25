package be.kdg.prog6.landSideBoundedContext.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import domain.MaterialType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
public class WeighInEvent {

    private UUID weighBridgeTicketId;

    private String licensePlate;

    private UUID sellerId;


    private double grossWeight;

    private MaterialType materialType;    private LocalDateTime weighInTime;


    public WeighInEvent(UUID weighBridgeTicketId, String licensePlate, UUID sellerId, double grossWeight, MaterialType materialType, LocalDateTime weighInTime) {
        this.weighBridgeTicketId = weighBridgeTicketId;
        this.licensePlate = licensePlate;
        this.sellerId = sellerId;
        this.grossWeight = grossWeight;
        this.materialType = materialType;
        this.weighInTime = weighInTime;
    }


}

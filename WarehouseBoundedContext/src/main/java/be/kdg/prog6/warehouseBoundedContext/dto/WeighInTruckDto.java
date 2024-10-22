package be.kdg.prog6.warehouseBoundedContext.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.UUID;


public record WeighInTruckDto(
        UUID weighBridgeTicketId,
        String licensePlate,
        UUID sellerId,
        String materialType,
//        @JsonSerialize(using = LocalDateTimeSerializer.class)
//        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        LocalDateTime weighInTime,
        double grossWeight
) {}

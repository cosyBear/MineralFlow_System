package be.kdg.prog6.landSideBoundedContext.adapters.in.dto;
//import be.kdg.prog6.landSideBoundedContext.util.LicensePlateDeserializer;

import be.kdg.prog6.landSideBoundedContext.domain.LicensePlate;

import java.time.LocalDateTime;

public record GateDto(
//        @JsonDeserialize(using = LicensePlateDeserializer.class)
        LicensePlate licensePlate, LocalDateTime localDate)  { }

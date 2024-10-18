package be.kdg.prog6.landSideBoundedContext.domain.dto;
//import be.kdg.prog6.landSideBoundedContext.util.LicensePlateDeserializer;

import be.kdg.prog6.landSideBoundedContext.domain.LicensePlate;

import java.time.LocalDateTime;

public record GateDto(
        LicensePlate licensePlate, LocalDateTime localDate)  { }

package be.kdg.prog6.LandSideBoundedContext.dto;
import be.kdg.prog6.LandSideBoundedContext.domain.*;
import be.kdg.prog6.LandSideBoundedContext.util.LicensePlateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;

public record GateDto(
        @JsonDeserialize(using = LicensePlateDeserializer.class)
        LicensePlate licensePlate, LocalDateTime localDate)  { }

package be.kdg.prog6.landSideBoundedContext.dto;
import be.kdg.prog6.landSideBoundedContext.domain.*;
//import be.kdg.prog6.landSideBoundedContext.util.LicensePlateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;

public record GateDto(
//        @JsonDeserialize(using = LicensePlateDeserializer.class)
        LicensePlate licensePlate, LocalDateTime localDate)  { }

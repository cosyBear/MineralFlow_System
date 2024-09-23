package be.kdg.prog6.LandSideBoundedContext.dto;
import be.kdg.prog6.LandSideBoundedContext.domain.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record GateDto(LicensePlate licensePlate, LocalDateTime localDate)  { }

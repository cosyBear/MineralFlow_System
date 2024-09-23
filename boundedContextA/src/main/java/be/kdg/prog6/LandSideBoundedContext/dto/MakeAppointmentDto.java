package be.kdg.prog6.LandSideBoundedContext.dto;

import be.kdg.prog6.LandSideBoundedContext.domain.LicensePlate;
import be.kdg.prog6.LandSideBoundedContext.domain.MaterialType;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record MakeAppointmentDto(
       // String companyName,
        String  sellerId,
        String  licensePlate,
        MaterialType materialType ,
       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime time

) {
}

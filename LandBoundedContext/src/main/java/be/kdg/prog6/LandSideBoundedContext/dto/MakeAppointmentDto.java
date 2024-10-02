package be.kdg.prog6.LandSideBoundedContext.dto;

import be.kdg.prog6.LandSideBoundedContext.domain.LicensePlate;
import be.kdg.prog6.LandSideBoundedContext.domain.MaterialType;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record MakeAppointmentDto(
       // String companyName,
        String  sellerId,
        String  licensePlate,
        MaterialType materialType ,
       @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
       LocalDateTime time,
       double payload


) {
}

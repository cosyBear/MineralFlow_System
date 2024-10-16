package be.kdg.prog6.landSideBoundedContext.adapters.in.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import domain.MaterialType;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record MakeAppointmentDto(
       // String companyName,
        UUID sellerId,
        String  licensePlate,
        MaterialType materialType ,
       @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
       LocalDateTime time

) {
}

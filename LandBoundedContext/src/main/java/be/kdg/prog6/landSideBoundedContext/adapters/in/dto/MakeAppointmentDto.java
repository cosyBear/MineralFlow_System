package be.kdg.prog6.landSideBoundedContext.adapters.in.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import domain.MaterialType;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record MakeAppointmentDto(
        UUID sellerId,
        String  licensePlate,
        MaterialType materialType ,
       LocalDateTime time

) {
}

package be.kdg.prog6.watersideboundedcontext.adapters.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record OperationRequestDto(
        String shipmentOrderId,
        LocalDateTime inspectionTimeOfSigning,
        String inspectorSignature,
        LocalDateTime bunkeringTime
) {}

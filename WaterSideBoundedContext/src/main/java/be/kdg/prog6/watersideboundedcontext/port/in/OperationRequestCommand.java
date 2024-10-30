package be.kdg.prog6.watersideboundedcontext.port.in;

import java.time.LocalDateTime;
import java.util.UUID;

public record OperationRequestCommand(    UUID shipmentOrderId,
                                          LocalDateTime inspectionTimeOfSigning,
                                          String inspectorSignature,
                                          LocalDateTime bunkeringTime
) {
}

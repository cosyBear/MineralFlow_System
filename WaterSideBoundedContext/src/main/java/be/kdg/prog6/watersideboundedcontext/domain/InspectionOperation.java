package be.kdg.prog6.watersideboundedcontext.domain;

import java.time.LocalDateTime;

public record InspectionOperation(LocalDateTime timeOfSigning, String signature) {
    public InspectionOperation {
    }

    public InspectionOperation() {
        this(null, null);
    }

}

package be.kdg.prog6.watersideboundedcontext.domain;

import java.time.LocalDateTime;

public record InspectionOperation(LocalDateTime timeOfSigning, String signature) {
    public InspectionOperation {
        // Allow timeOfSigning and signature to be null or default values
    }

    // Overloaded constructor to create an empty InspectionOperation
    public InspectionOperation() {
        this(null, null);
    }

}

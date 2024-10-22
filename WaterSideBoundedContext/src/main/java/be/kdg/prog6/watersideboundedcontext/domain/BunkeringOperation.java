package be.kdg.prog6.watersideboundedcontext.domain;

import java.time.LocalDateTime;

public record BunkeringOperation(LocalDateTime bunkeringTime) {

    public BunkeringOperation {
        // Allow bunkeringTime to be null
    }

    // Overloaded constructor to create an empty BunkeringOperation
    public BunkeringOperation() {
        this(null);
    }
}

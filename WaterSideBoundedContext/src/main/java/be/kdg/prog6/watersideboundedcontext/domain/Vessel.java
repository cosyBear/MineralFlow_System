package be.kdg.prog6.watersideboundedcontext.domain;

import java.util.UUID;

public record Vessel(UUID Vessel) {

    public Vessel() {
        this(UUID.randomUUID());
    }

}

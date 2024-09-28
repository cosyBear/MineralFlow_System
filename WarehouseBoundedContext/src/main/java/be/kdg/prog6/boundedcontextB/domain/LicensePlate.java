package be.kdg.prog6.boundedcontextB.domain;

import be.kdg.prog6.boundedcontextB.util.LicensePlateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = LicensePlateDeserializer.class)

public record LicensePlate(String licensePlate) {

    public LicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}

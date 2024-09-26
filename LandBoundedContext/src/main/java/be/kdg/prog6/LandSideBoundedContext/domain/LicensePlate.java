package be.kdg.prog6.LandSideBoundedContext.domain;

import be.kdg.prog6.LandSideBoundedContext.util.LicensePlateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = LicensePlateDeserializer.class)

public record LicensePlate(String licensePlate) {

    public LicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}

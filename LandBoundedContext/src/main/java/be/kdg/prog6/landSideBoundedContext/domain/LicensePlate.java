package be.kdg.prog6.landSideBoundedContext.domain;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

public record LicensePlate(String licensePlate) {
    @JsonCreator
    public static LicensePlate fromString(String licensePlate) {
        return new LicensePlate(licensePlate.toUpperCase());
    }

    @JsonValue
    public String toString() {
        return licensePlate;
    }




}

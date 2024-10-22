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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LicensePlate that = (LicensePlate) o;
        return Objects.equals(licensePlate, that.licensePlate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(licensePlate);
    }
}

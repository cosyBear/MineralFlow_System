package be.kdg.prog6.boundedcontextB.util;

import be.kdg.prog6.boundedcontextB.domain.LicensePlate;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class LicensePlateDeserializer extends JsonDeserializer<LicensePlate> {
    @Override
    public LicensePlate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return new LicensePlate(p.getText());
    }
}
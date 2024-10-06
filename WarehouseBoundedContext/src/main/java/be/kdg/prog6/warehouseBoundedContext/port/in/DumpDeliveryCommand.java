package be.kdg.prog6.warehouseBoundedContext.port.in;

import be.kdg.prog6.warehouseBoundedContext.domain.MaterialType;

public class DumpDeliveryCommand {
    private final String licensePlate;
    private final double payload;
    private final MaterialType materialType;

    public DumpDeliveryCommand(String licensePlate, double payload, MaterialType materialType) {
        this.licensePlate = licensePlate;
        this.payload = payload;
        this.materialType = materialType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public double getPayload() {
        return payload;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }
}

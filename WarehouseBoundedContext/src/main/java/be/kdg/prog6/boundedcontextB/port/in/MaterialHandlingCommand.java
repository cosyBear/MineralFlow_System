package be.kdg.prog6.boundedcontextB.port.in;

import be.kdg.prog6.boundedcontextB.domain.MaterialType;
import be.kdg.prog6.boundedcontextB.domain.SellerId;

public class MaterialHandlingCommand {


    private final MaterialType materialType;
    private final SellerId sellerId;
    private final double payload; // Payload in tons

    public MaterialHandlingCommand(MaterialType materialType, SellerId sellerId, double payload) {
        this.materialType = materialType;
        this.sellerId = sellerId;
        this.payload = payload;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public SellerId getSellerId() {
        return sellerId;
    }

    public double getPayload() {
        return payload;
    }
}

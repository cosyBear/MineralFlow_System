package be.kdg.prog6.warehouseBoundedContext.port.in;

import be.kdg.prog6.warehouseBoundedContext.domain.SellerId;
import domain.MaterialType;

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

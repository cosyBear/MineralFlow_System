package be.kdg.prog6.warehouseBoundedContext.port.in;

import domain.MaterialType;

public record OrderLineCommand(MaterialType materialType, double quantity, Double pricePerTon) {
}

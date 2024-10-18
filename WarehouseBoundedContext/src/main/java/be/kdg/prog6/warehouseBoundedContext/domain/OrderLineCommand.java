package be.kdg.prog6.warehouseBoundedContext.domain;

import domain.MaterialType;

public record OrderLineCommand(MaterialType materialType, double quantity, Double pricePerTon) {
}

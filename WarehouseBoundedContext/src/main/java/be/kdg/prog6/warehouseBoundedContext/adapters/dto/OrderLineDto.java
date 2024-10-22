package be.kdg.prog6.warehouseBoundedContext.adapters.dto;

public record OrderLineDto(String materialType,
                           double quantity,
                           Double pricePerTon) {
}

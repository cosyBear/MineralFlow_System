package be.kdg.prog6.warehouseBoundedContext.domain;


import domain.MaterialType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PurchaseOrderLine {
    private MaterialType materialType;
    private double quantity;
    private double pricePerTon;

    public PurchaseOrderLine(MaterialType materialType, double quantity, double pricePerTon) {
        this.materialType = materialType;
        this.quantity = quantity;
        this.pricePerTon = pricePerTon;
    }

    @Override
    public String toString() {
        return "PurchaseOrderLine{" +
                "materialType=" + materialType +
                ", quantityInTons=" + quantity +
                ", pricePerTon=" + pricePerTon +
                '}';
    }
}

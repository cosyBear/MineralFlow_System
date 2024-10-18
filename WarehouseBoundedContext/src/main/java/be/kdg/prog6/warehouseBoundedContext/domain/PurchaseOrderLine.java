package be.kdg.prog6.warehouseBoundedContext.domain;


import domain.MaterialType;

public class PurchaseOrderLine {
    private MaterialType materialType;   // The type of material being purchased
    private double quantity;          // The quantity being purchased in tons
    private double pricePerTon;          // The price per ton of the material

    public PurchaseOrderLine(MaterialType materialType, double quantity, double pricePerTon) {
        this.materialType = materialType;
        this.quantity = quantity;
        this.pricePerTon = pricePerTon;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPricePerTon() {
        return pricePerTon;
    }

    public void setPricePerTon(double pricePerTon) {
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

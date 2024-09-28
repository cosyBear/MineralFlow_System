package be.kdg.prog6.boundedcontextB.domain;

import java.time.LocalDateTime;

public class Warehouse {
    private MaterialType materialType;
    private SellerId sellerId;
    private Integer warehouseNumber;
    private boolean capacityReached; // this is in percentage  to how much full is the  warehouse
    private double amountOfMaterial;


    public PDT addMaterial(Truck truck ) {

        //later add some checks to see   if you can add materials to the warehouse  here in this method or before
        // what do i mean maybe the warehouse is almost full and we can not add  material in.
        this.materialType = truck.getMaterialType();
        this.amountOfMaterial += truck.getPayload();

        return new PDT(truck.getMaterialType() , LocalDateTime.now() , truck.getPayload());
    }

    public void removeMaterial(double amount ) {
        this.amountOfMaterial -= amount;
    }

    public boolean isWarehouseAvailable(){
        return materialType == null && amountOfMaterial == 0;
    }




    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public SellerId getSellerId() {
        return sellerId;
    }

    public void setSellerId(SellerId sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(Integer warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }

    public boolean isCapacityReached() {
        return capacityReached;
    }

    public void setCapacityReached(boolean capacityReached) {
        this.capacityReached = capacityReached;
    }

    public double getAmountOfMaterial() {
        return amountOfMaterial;
    }

    public void setAmountOfMaterial(double amountOfMaterial) {
        this.amountOfMaterial = amountOfMaterial;
    }
}

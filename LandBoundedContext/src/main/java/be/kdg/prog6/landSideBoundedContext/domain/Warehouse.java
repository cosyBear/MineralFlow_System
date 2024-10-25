package be.kdg.prog6.landSideBoundedContext.domain;

import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import be.kdg.prog6.landSideBoundedContext.domain.Id.WarehouseId;
import be.kdg.prog6.landSideBoundedContext.util.errorClasses.InsufficientMaterialException;
import domain.MaterialType;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Warehouse {

    private static final double MAX_CAPACITY = 500.0;
    private static final double OVERFLOW_CAPACITY = MAX_CAPACITY * 1.1;
    private static final double FULL_THRESHOLD = MAX_CAPACITY * 0.8;

    private WarehouseId warehouseId;

    private SellerId sellerId;

    private MaterialType materialType;

    private double amountOfMaterial;

    public Warehouse() {

    }

    public Warehouse(WarehouseId warehouseId, SellerId sellerId, MaterialType materialType) {
        this.warehouseId = warehouseId;
        this.sellerId = sellerId;
        this.materialType = materialType;
    }

    public void addMaterial(double amount) {
        this.amountOfMaterial += amount;
    }

    public Warehouse(WarehouseId warehouseId, SellerId sellerId, MaterialType materialType, double amountOfMaterial) {
        this.warehouseId = warehouseId;
        this.sellerId = sellerId;
        this.materialType = materialType;
        this.amountOfMaterial = amountOfMaterial;
    }
    public void subtractMaterial(double amount) {
        if (this.amountOfMaterial >= amount) {
            this.amountOfMaterial -= amount;
        } else {
            throw new InsufficientMaterialException("Not enough material in the warehouse to subtract the requested amount");
        }
    }


    public void updateMaterial(double amount){
        this.amountOfMaterial = amount;
    }


    public boolean canStoreMaterial(double amount) {
        return this.amountOfMaterial + amount <= MAX_CAPACITY;
    }


    public boolean isFull() {
        return this.amountOfMaterial >= FULL_THRESHOLD;  // Warehouse is considered full at 80% capacity
    }

    public boolean isAtOverflow() {
        return this.amountOfMaterial >= MAX_CAPACITY && this.amountOfMaterial <= OVERFLOW_CAPACITY;
    }

    public boolean isOverCapacity() {
        return this.amountOfMaterial > OVERFLOW_CAPACITY;
    }

    public double availableCapacity() {
        return MAX_CAPACITY - this.amountOfMaterial;
    }



}

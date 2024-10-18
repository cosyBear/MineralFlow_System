package be.kdg.prog6.landSideBoundedContext.domain;

import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import be.kdg.prog6.landSideBoundedContext.domain.Id.WarehouseId;
import domain.MaterialType;

public class Warehouse {

    private static final double MAX_CAPACITY = 500.0;  // Maximum capacity in tons
    private static final double OVERFLOW_CAPACITY = MAX_CAPACITY * 1.1;  // Overflow capacity (110%)
    private static final double FULL_THRESHOLD = MAX_CAPACITY * 0.8;  // 80% threshold

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

    public WarehouseId getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(WarehouseId warehouseId) {
        this.warehouseId = warehouseId;
    }

    public SellerId getSellerId() {
        return sellerId;
    }

    public void setSellerId(SellerId sellerId) {
        this.sellerId = sellerId;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public double getAmountOfMaterial() {
        return amountOfMaterial;
    }

    public void setAmountOfMaterial(double amountOfMaterial) {
        this.amountOfMaterial = amountOfMaterial;
    }


    public boolean canStoreMaterial(double amount) {
        // Check if the current amount of material plus the new amount does not exceed the maximum capacity
        return this.amountOfMaterial + amount <= MAX_CAPACITY;
    }


    // Method to check if the warehouse is full
    public boolean isFull() {
        return this.amountOfMaterial >= FULL_THRESHOLD;  // Warehouse is considered full at 80% capacity
    }

    // Method to check if the warehouse is at overflow capacity
    public boolean isAtOverflow() {
        return this.amountOfMaterial >= MAX_CAPACITY && this.amountOfMaterial <= OVERFLOW_CAPACITY;
    }

    // Method to check if the warehouse has exceeded its capacity (over 110%)
    public boolean isOverCapacity() {
        return this.amountOfMaterial > OVERFLOW_CAPACITY;
    }

    // Method to calculate available capacity in the warehouse
    public double availableCapacity() {
        return MAX_CAPACITY - this.amountOfMaterial;
    }

    public WarehouseStatus checkWarehouseCapacity() {
        if (this.isOverCapacity()) {
            return WarehouseStatus.FULL_OVERFLOW; // Warehouse exceeds overflow capacity (110%)
        } else if (this.isAtOverflow()) {
            return WarehouseStatus.OVERFLOW; // Warehouse is at overflow (100%-110%)
        } else if (this.isFull()) {
            return WarehouseStatus.FULL; // Warehouse is full (80%-100%)
        }
        return WarehouseStatus.ALREADY_EXISTS_NOT_FULL; // Warehouse exists and has space
    }


}

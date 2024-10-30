package be.kdg.prog6.landSideBoundedContext.domain;

import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import be.kdg.prog6.landSideBoundedContext.domain.Id.WarehouseId;
import util.errorClasses.InsufficientMaterialException;
import domain.MaterialType;
import lombok.Getter;
import lombok.Setter;
import util.errorClasses.WarehouseIsFullException;


@Setter
@Getter
public class Warehouse {

    private static final double MAX_CAPACITY = 500000.0;
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


    public Warehouse(WarehouseId warehouseId, SellerId sellerId, MaterialType materialType, double amountOfMaterial) {
        this.warehouseId = warehouseId;
        this.sellerId = sellerId;
        this.materialType = materialType;
        this.amountOfMaterial = amountOfMaterial;
    }



    public void updateMaterial(double amount){
        this.amountOfMaterial = amount;
    }

    public boolean canStoreMaterial(double amount) {
        return amount <= MAX_CAPACITY;
    }


    public boolean isFull() {
        return this.amountOfMaterial >= FULL_THRESHOLD;
    }


    public double availableCapacity() {
        return MAX_CAPACITY - this.amountOfMaterial;
    }



}

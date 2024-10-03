package be.kdg.prog6.landSideBoundedContext.adapters.out.entity;


import be.kdg.prog6.landSideBoundedContext.domain.MaterialType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(catalog = "app_db")
public class WareHouseEntity {


    @Id
    private UUID warehouseId;
    private UUID sellerId;
    private double amountOfMaterial;
    private MaterialType materialType;


    public WareHouseEntity() {

    }

    public WareHouseEntity(UUID warehouseId, UUID sellerId, double amountOfMaterial, MaterialType materialType) {
        this.warehouseId = warehouseId;
        this.sellerId = sellerId;
        this.amountOfMaterial = amountOfMaterial;
        this.materialType = materialType;
    }

    public UUID getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(UUID warehouseId) {
        this.warehouseId = warehouseId;
    }

    public UUID getSellerId() {
        return sellerId;
    }

    public void setSellerId(UUID sellerId) {
        this.sellerId = sellerId;
    }

    public double getAmountOfMaterial() {
        return amountOfMaterial;
    }

    public void setAmountOfMaterial(double amountOfMaterial) {
        this.amountOfMaterial = amountOfMaterial;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }
}

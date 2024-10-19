package be.kdg.prog6.landSideBoundedContext.domain;

import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import domain.MaterialType;

import java.time.LocalDateTime;
import java.util.UUID;

public class TruckOnSiteQuery {

    private MaterialType materialType;
    private LocalDateTime time;
    private UUID sellerId;
    private String licensePlate;

    public TruckOnSiteQuery() {

    }

    public TruckOnSiteQuery(MaterialType materialType, LocalDateTime time, UUID sellerId, String licensePlate) {
        this.materialType = materialType;
        this.time = time;
        this.sellerId = sellerId;
        this.licensePlate = licensePlate;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public UUID getSellerId() {
        return sellerId;
    }

    public void setSellerId(UUID sellerId) {
        this.sellerId = sellerId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public String toString() {
        return "AppointmentQuery[" +
                "materialType=" + materialType + ", " +
                "time=" + time + ", " +
                "sellerId=" + sellerId + ", " +
                "licensePlate=" + licensePlate;
    }
}

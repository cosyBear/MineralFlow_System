package be.kdg.prog6.landSideBoundedContext.domain;
import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;

import java.time.LocalDateTime;

public class Appointment {
    private MaterialType materialType;
    private LocalDateTime time; // replace withTime
    private SellerId sellerId;
    private LicensePlate licensePlate;
    private double payload;

    public Appointment(){

    }
    public Appointment(MaterialType materialType, LocalDateTime date, SellerId sellerId, LicensePlate licensePlate , double payload) {
        this.materialType = materialType;
        this.time = date;
        this.sellerId = sellerId;
        this.licensePlate = licensePlate;
        this.payload = payload;
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

    public SellerId getSellerId() {
        return sellerId;
    }

    public void setSellerId(SellerId sellerId) {
        this.sellerId = sellerId;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(LicensePlate licensePlate) {
        this.licensePlate = licensePlate;
    }

    public double getPayload() {
        return payload;
    }

    public void setPayload(double payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "materialType=" + materialType +
                ", time=" + time +
                ", sellerId=" + sellerId +
                ", licensePlate=" + licensePlate +
                '}';
    }
}

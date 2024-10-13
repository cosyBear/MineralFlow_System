package be.kdg.prog6.landSideBoundedContext.domain;

import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;

import java.time.LocalDateTime;

public class AppointmentQuery {

    private be.kdg.prog6.landSideBoundedContext.domain.AppointmentStatus AppointmentStatus;
    private MaterialType materialType;
    private  LocalDateTime time;
    private  SellerId sellerId;
    private LicensePlate licensePlate;
    private  double payload;
    public AppointmentQuery(){

    }

    public AppointmentQuery(AppointmentStatus AppointmentStatus, MaterialType materialType, LocalDateTime time, SellerId sellerId, LicensePlate licensePlate, double payload) {
        this.AppointmentStatus = AppointmentStatus;
        this.materialType = materialType;
        this.time = time;
        this.sellerId = sellerId;
        this.licensePlate = licensePlate;
        this.payload = payload;
    }

    public AppointmentStatus getAppointmentStatus() {
        return AppointmentStatus;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.AppointmentStatus = appointmentStatus;
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
        return "AppointmentQuery[" +
                "onTime=" + AppointmentStatus + ", " +
                "materialType=" + materialType + ", " +
                "time=" + time + ", " +
                "sellerId=" + sellerId + ", " +
                "licensePlate=" + licensePlate + ", " +
                "payload=" + payload + ']';
    }
}

package be.kdg.prog6.LandSideBoundedContext.domain;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Appointment {
    // needs id maybe
    private MaterialType materialType;
    private LocalDateTime date; // replace withTime
    private SellerId sellerId;
    private LicensePlate licensePlate;

    public Appointment(MaterialType materialType, LocalDateTime date, SellerId sellerId, LicensePlate licensePlate) {
        this.materialType = materialType;
        this.date = date;
        this.sellerId = sellerId;
        this.licensePlate = licensePlate;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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
}

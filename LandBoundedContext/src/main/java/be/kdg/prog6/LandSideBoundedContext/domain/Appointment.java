package be.kdg.prog6.LandSideBoundedContext.domain;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Appointment {
    // needs id maybe
    private MaterialType materialType;
    private LocalDateTime time; // replace withTime
    private SellerId sellerId;
    private LicensePlate licensePlate;

    public Appointment(){

    }
    public Appointment(MaterialType materialType, LocalDateTime date, SellerId sellerId, LicensePlate licensePlate) {
        this.materialType = materialType;
        this.time = date;
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

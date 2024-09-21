package be.kdg.prog6.LandSideBoundedContext.domain;
import java.time.LocalDate;

public class Appointment {
    private String companyName;
    private TimeSlot TimeSlot;
    private Truck Truck;
    private MaterialType materialType;
    private LocalDate date;


    public Appointment() {
    }

    public Appointment(TimeSlot timeSlot, Truck truck, MaterialType materialType, LocalDate date , String companyName) {
        TimeSlot = timeSlot;
        Truck = truck;
        this.materialType = materialType;
        this.date = date;
        this.companyName = companyName;
    }


    public TimeSlot getTimeSlot() {
        return TimeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        TimeSlot = timeSlot;
    }

    public be.kdg.prog6.LandSideBoundedContext.domain.Truck getTruck() {
        return Truck;
    }

    public void setTruck(Truck truck) {
        Truck = truck;
    }


    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }


    public void setDate(LocalDate date) {
        this.date = date;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}

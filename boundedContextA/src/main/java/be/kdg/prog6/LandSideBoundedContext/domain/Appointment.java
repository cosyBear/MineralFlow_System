package be.kdg.prog6.LandSideBoundedContext.domain;
import be.kdg.prog6.LandSideBoundedContext.domain.TimeSlot;
import java.time.LocalDate;
import be.kdg.prog6.LandSideBoundedContext.domain.Truck ;
public class Appointment {
    private TimeSlot TimeSlot;
    private Truck Truck;
    private MaterialType materialType;
    private LocalDate date;


    public Appointment( TimeSlot timeSlot, Truck truck, MaterialType materialType, LocalDate date) {
        TimeSlot = timeSlot;
        Truck = truck;
        this.materialType = materialType;
        this.date = date;
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
}

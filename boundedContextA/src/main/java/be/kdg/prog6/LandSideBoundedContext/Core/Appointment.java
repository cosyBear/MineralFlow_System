package be.kdg.prog6.LandSideBoundedContext.Core;

public class Appointment {
    private TimeSlot TimeSlot;
    private Truck Truck;
    private MaterialType materialType;



    public Appointment(TimeSlot timeSlot, MaterialType materialType, Truck truck) {
        this.TimeSlot = timeSlot;
        this.materialType = materialType;
        this.Truck = truck;
    }


    public TimeSlot getTimeSlot() {
        return TimeSlot;
    }

    public Truck getTruck() {
        return Truck;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }
}

package be.kdg.prog6.LandSideBoundedContext.Core;

public class Appointment {
    private TimeSlot TimeSlot;
    private Truck Truck;
    private MaterialType materialType;


    public Appointment(TimeSlot timeSlot, Truck truck , MaterialType materialType) {
        TimeSlot = timeSlot;
        Truck = truck;
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

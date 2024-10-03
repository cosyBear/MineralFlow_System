package be.kdg.prog6.landSideBoundedContext.util.errorClasses;

public class TruckIsNotOnTime extends RuntimeException {
    public TruckIsNotOnTime() {
        super("Truck is not on time");
    }
}

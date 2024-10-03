package be.kdg.prog6.landSideBoundedContext.util.errorClasses;

public class TruckIsNotAllowedToEnter extends RuntimeException{
    public TruckIsNotAllowedToEnter(String message) {
        super(message);
    }
}

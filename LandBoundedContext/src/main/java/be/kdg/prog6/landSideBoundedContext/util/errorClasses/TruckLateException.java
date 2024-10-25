package be.kdg.prog6.landSideBoundedContext.util.errorClasses;

public class TruckLateException extends RuntimeException {
    public TruckLateException(String message) {
        super(message);
    }
}
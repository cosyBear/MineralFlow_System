package be.kdg.prog6.landSideBoundedContext.util.errorClasses;


public class TimeSlotFullException extends RuntimeException {
    public TimeSlotFullException(String message) {
        super(message);
    }
}

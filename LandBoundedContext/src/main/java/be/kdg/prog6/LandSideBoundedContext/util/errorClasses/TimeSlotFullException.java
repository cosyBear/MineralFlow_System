package be.kdg.prog6.LandSideBoundedContext.util.errorClasses;


public class TimeSlotFullException extends RuntimeException {
    public TimeSlotFullException(String message) {
        super(message);
    }
}

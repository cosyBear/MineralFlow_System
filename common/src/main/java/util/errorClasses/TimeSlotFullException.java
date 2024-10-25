package util.errorClasses;


public class TimeSlotFullException extends RuntimeException {
    public TimeSlotFullException(String message) {
        super(message);
    }
}

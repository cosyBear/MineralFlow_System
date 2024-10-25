package util.errorClasses;

public class TruckLateException extends RuntimeException {
    public TruckLateException(String message) {
        super(message);
    }
}
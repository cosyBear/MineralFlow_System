package util.errorClasses;

public class ShipmentOrderNotFoundException extends RuntimeException {
    public ShipmentOrderNotFoundException(String message) {
        super(message);
    }
}
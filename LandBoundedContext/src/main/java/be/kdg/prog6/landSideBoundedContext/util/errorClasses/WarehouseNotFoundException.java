package be.kdg.prog6.landSideBoundedContext.util.errorClasses;

public class WarehouseNotFoundException extends RuntimeException{
    public WarehouseNotFoundException(String message) {
        super(message);
    }
}

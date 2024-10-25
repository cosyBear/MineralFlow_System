package be.kdg.prog6.warehouseBoundedContext.util.Error;

public class WarehouseNotFoundException extends RuntimeException {
    public WarehouseNotFoundException(String message) {
        super(message);
    }
}
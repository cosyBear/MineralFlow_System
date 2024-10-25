package be.kdg.prog6.warehouseBoundedContext.util.Error;

public class WarehouseDatabaseException extends RuntimeException {
    public WarehouseDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
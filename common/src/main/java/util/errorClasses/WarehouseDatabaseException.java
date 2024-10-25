package util.errorClasses;

public class WarehouseDatabaseException extends RuntimeException {
    public WarehouseDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
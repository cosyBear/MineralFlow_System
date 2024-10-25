package util.errorClasses;

public class PurchaseOrderDatabaseException extends RuntimeException {
    public PurchaseOrderDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
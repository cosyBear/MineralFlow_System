package be.kdg.prog6.warehouseBoundedContext.util.Error;

public class PurchaseOrderDatabaseException extends RuntimeException {
    public PurchaseOrderDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
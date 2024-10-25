package be.kdg.prog6.warehouseBoundedContext.util.Error;

public class PurchaseOrderNotFoundException extends RuntimeException {
    public PurchaseOrderNotFoundException(String message) {
        super(message);
    }
}
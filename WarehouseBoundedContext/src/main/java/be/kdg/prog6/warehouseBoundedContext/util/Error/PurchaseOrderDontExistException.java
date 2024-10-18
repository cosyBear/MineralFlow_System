package be.kdg.prog6.warehouseBoundedContext.util.Error;

public class PurchaseOrderDontExistException extends RuntimeException {
    public PurchaseOrderDontExistException(String message ) {
        super(message);
    }
}

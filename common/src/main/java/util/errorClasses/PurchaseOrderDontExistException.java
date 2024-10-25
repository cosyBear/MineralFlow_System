package util.errorClasses;

public class PurchaseOrderDontExistException extends RuntimeException {
    public PurchaseOrderDontExistException(String message ) {
        super(message);
    }
}

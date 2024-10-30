package util.errorClasses;

public class PurchaseOrderIsFulfilledException extends RuntimeException {
    public PurchaseOrderIsFulfilledException(String message) {
        super(message);
    }
}

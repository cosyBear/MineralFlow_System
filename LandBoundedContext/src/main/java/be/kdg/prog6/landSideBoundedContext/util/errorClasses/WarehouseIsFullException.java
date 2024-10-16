package be.kdg.prog6.landSideBoundedContext.util.errorClasses;

public class WarehouseIsFullException extends RuntimeException{
    public WarehouseIsFullException(String message ) {
        super(message);
    }
}

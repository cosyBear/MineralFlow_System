package util.errorClasses;

public class WarehouseIsFullException extends RuntimeException{
    public WarehouseIsFullException(String message ) {
        super(message);
    }
}

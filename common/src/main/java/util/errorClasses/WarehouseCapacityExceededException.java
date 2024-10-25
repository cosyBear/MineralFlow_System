package util.errorClasses;

public class WarehouseCapacityExceededException extends RuntimeException{
    public WarehouseCapacityExceededException(String message) {
        super(message);

    }
}

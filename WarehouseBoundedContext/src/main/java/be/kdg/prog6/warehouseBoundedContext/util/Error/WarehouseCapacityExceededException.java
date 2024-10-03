package be.kdg.prog6.warehouseBoundedContext.util.Error;

public class WarehouseCapacityExceededException extends RuntimeException{
    public WarehouseCapacityExceededException(String message) {
        super(message);

    }
}

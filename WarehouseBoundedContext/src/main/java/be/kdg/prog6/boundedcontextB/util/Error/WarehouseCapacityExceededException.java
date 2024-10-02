package be.kdg.prog6.boundedcontextB.util.Error;

public class WarehouseCapacityExceededException extends RuntimeException{
    public WarehouseCapacityExceededException(String message) {
        super(message);

    }
}

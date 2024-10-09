package be.kdg.prog6.landSideBoundedContext.util.errorClasses;

public class WarehouseISFull extends RuntimeException{
    public WarehouseISFull(String message ) {
        super(message);
    }
}

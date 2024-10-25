package be.kdg.prog6.warehouseBoundedContext.util.Error;

public class NotEnoughMaterialException extends RuntimeException{
    public NotEnoughMaterialException(String message) {
        super(message);
    }
}

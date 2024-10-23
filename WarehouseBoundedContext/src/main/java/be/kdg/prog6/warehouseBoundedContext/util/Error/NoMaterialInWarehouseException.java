package be.kdg.prog6.warehouseBoundedContext.util.Error;

public class NoMaterialInWarehouseException extends RuntimeException{
    public NoMaterialInWarehouseException(String message) {
        super(message);
    }
}

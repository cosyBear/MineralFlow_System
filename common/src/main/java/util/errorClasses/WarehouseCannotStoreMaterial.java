package util.errorClasses;

public class WarehouseCannotStoreMaterial extends  RuntimeException{
    public WarehouseCannotStoreMaterial(String message) {
        super(message);
    }
}

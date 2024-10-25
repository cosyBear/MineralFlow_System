package util.errorClasses;

public class NotEnoughMaterialException extends RuntimeException{
    public NotEnoughMaterialException(String message) {
        super(message);
    }
}

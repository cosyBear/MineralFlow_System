package be.kdg.prog6.landSideBoundedContext.util.errorClasses;

public class InsufficientMaterialException extends RuntimeException {
    public InsufficientMaterialException(String message) {
        super(message);
    }
}
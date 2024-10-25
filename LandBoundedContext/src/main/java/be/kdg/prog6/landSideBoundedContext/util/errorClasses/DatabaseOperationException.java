package be.kdg.prog6.landSideBoundedContext.util.errorClasses;

public class DatabaseOperationException extends RuntimeException {
    public DatabaseOperationException(String message) {
        super(message);
    }
}

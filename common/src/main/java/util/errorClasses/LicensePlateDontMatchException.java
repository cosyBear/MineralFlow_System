package util.errorClasses;

public class LicensePlateDontMatchException extends RuntimeException {
    public LicensePlateDontMatchException(String message) {
        super(message);
    }
}

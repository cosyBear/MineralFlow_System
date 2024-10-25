package util.errorClasses;

public class CouldNotSaveAppointmentException extends RuntimeException{
    public CouldNotSaveAppointmentException(String message)
    {
        super(message);
    }
}

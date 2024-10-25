package be.kdg.prog6.landSideBoundedContext.util.errorClasses;

public class CouldNotSaveAppointmentException extends RuntimeException{
    public CouldNotSaveAppointmentException(String message)
    {
        super(message);
    }
}

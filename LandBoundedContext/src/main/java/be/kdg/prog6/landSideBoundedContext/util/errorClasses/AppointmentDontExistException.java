package be.kdg.prog6.landSideBoundedContext.util.errorClasses;

public class AppointmentDontExistException extends  RuntimeException{

    public AppointmentDontExistException(String message){
        super(message);
    }

}

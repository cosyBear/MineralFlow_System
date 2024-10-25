package util.errorClasses;

public class AppointmentDontExistException extends  RuntimeException{

    public AppointmentDontExistException(String message){
        super(message);
    }

}

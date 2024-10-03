package be.kdg.prog6.landSideBoundedContext.util.errorClasses;

public class AppointmentDontExist extends  RuntimeException{

    public AppointmentDontExist(String message){
        super(message);
    }

}

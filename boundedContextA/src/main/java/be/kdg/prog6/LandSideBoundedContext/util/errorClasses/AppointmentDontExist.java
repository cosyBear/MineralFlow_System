package be.kdg.prog6.LandSideBoundedContext.util.errorClasses;

public class AppointmentDontExist extends  RuntimeException{

    public AppointmentDontExist(String message){
        super(message);
    }

}

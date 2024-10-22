package be.kdg.prog6.watersideboundedcontext.util;

public class NoShipmentOrderException extends  RuntimeException{
    public NoShipmentOrderException(String msg){
        super(msg);
    }
}

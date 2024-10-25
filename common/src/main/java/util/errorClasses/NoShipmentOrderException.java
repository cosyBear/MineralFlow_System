package util.errorClasses;

public class NoShipmentOrderException extends  RuntimeException{
    public NoShipmentOrderException(String msg){
        super(msg);
    }
}

package be.kdg.prog6.watersideboundedcontext.port.out;

public interface ShipmentOrderEventPublisher {
    void requestMaterialEvent(RequestMaterialEvent event);
}

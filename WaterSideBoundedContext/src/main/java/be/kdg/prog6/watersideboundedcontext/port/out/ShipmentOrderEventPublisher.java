package be.kdg.prog6.watersideboundedcontext.port.out;

import be.kdg.prog6.watersideboundedcontext.port.in.RequestMaterialEvent;

public interface ShipmentOrderEventPublisher {
    void requestMaterialEvent(RequestMaterialEvent event);
}

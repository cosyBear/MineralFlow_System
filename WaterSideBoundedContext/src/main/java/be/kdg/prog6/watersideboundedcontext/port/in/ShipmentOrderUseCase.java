package be.kdg.prog6.watersideboundedcontext.port.in;

public interface ShipmentOrderUseCase {

    void requestMaterial(RequestMaterialCommand shipmentOrder);
    void shipDeparture(ShipmentCompletedCommand shipmentOrder);
}

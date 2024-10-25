package be.kdg.prog6.watersideboundedcontext.port.in;

public interface ShipmentOrderUseCase {

    void requestMaterial(ShipmentOrderCommand shipmentOrder);
    boolean shipDeparture(ShipmentCompletedCommand shipmentOrder);
}

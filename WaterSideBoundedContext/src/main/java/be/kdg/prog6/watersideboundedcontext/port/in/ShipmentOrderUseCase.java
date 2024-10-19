package be.kdg.prog6.watersideboundedcontext.port.in;

import be.kdg.prog6.watersideboundedcontext.domain.ShipmentOrder;

public interface ShipmentOrderUseCase {

    void requestMaterial(ShipmentOrderCommand shipmentOrder);

}

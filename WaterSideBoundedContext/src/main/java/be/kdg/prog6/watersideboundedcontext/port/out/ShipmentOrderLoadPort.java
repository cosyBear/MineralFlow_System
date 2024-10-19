package be.kdg.prog6.watersideboundedcontext.port.out;

import be.kdg.prog6.watersideboundedcontext.domain.ShipmentOrder;

import java.util.UUID;

public interface ShipmentOrderLoadPort {

    ShipmentOrder loadShipmentOrderById(UUID shipmentOrderId);
}

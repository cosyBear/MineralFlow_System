package be.kdg.prog6.watersideboundedcontext.port.out;

import be.kdg.prog6.watersideboundedcontext.domain.ShipmentOrder;

public interface ShipmentOrderSavePort {


    void Save(ShipmentOrder order);
}

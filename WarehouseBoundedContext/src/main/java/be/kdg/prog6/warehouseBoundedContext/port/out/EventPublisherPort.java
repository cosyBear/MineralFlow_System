package be.kdg.prog6.warehouseBoundedContext.port.out;

import be.kdg.prog6.warehouseBoundedContext.port.in.WarehouseMaterialEvent;

public interface EventPublisherPort {

    public void publishWarehouseMaterialEvent(WarehouseMaterialEvent warehouseMaterialEvent);
}

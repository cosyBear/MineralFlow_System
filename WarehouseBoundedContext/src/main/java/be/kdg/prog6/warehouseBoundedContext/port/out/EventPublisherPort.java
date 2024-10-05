package be.kdg.prog6.warehouseBoundedContext.port.out;

import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseMaterialEvent;

public interface EventPublisherPort {

    public void publishPDTCompletedEvent(WarehouseMaterialEvent warehouseMaterialEvent);
}

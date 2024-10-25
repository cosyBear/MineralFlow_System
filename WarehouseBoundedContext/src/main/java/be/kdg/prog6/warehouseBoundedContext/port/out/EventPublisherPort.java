package be.kdg.prog6.warehouseBoundedContext.port.out;

public interface EventPublisherPort {

    public void publishWarehouseMaterialEvent(WarehouseMaterialEvent warehouseMaterialEvent);
}

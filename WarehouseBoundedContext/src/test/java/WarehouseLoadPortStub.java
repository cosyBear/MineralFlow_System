import be.kdg.prog6.warehouseBoundedContext.domain.SellerId;
import be.kdg.prog6.warehouseBoundedContext.domain.Warehouse;
import be.kdg.prog6.warehouseBoundedContext.port.out.Warehouse.WarehouseLoadPort;
import domain.MaterialType;

import java.util.List;
import java.util.UUID;
import be.kdg.prog6.warehouseBoundedContext.domain.SellerId;
import be.kdg.prog6.warehouseBoundedContext.domain.Warehouse;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseEventsWindow;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseId;
import be.kdg.prog6.warehouseBoundedContext.port.out.Warehouse.WarehouseLoadPort;
import domain.MaterialType;

import java.time.LocalDateTime;
import java.util.UUID;

public class WarehouseLoadPortStub implements WarehouseLoadPort {

    private final Warehouse defaultWarehouse;

    public WarehouseLoadPortStub() {
        this.defaultWarehouse = new Warehouse(
                new WarehouseId(UUID.randomUUID()),
                new SellerId(UUID.randomUUID()),
                MaterialType.IRON
        );
        WarehouseEventsWindow eventsWindow = defaultWarehouse.getEventsWindow();
        eventsWindow.addMaterialWeightEvent(LocalDateTime.now(), 100.0, UUID.randomUUID(), MaterialType.IRON);
    }

    @Override
    public Warehouse fetchWarehouseWithEvents(UUID warehouseId) {
        return warehouseId.equals(defaultWarehouse.getWarehouseNumber().getId()) ? defaultWarehouse : null;
    }

    @Override
    public Warehouse findBySellerId(SellerId sellerId) {
        return sellerId.equals(defaultWarehouse.getSellerId()) ? defaultWarehouse : null;
    }

    @Override
    public Warehouse findBySellerIdAndWarehouseId(SellerId sellerId, UUID warehouseId) {
        return sellerId.equals(defaultWarehouse.getSellerId()) &&
                warehouseId.equals(defaultWarehouse.getWarehouseNumber().getId()) ? defaultWarehouse : null;
    }

    @Override
    public Warehouse findBySellerIdAndMaterialType(SellerId sellerId, MaterialType materialType) {
        return sellerId.equals(defaultWarehouse.getSellerId()) &&
                materialType == defaultWarehouse.getMaterialType() ? defaultWarehouse : null;
    }

    @Override
    public List<Warehouse> loadAllWarehouses() {
        return List.of();
    }

    // Getter for the default warehouse's SellerId and MaterialType
    public SellerId getDefaultSellerId() {
        return defaultWarehouse.getSellerId();
    }

    public MaterialType getDefaultMaterialType() {
        return defaultWarehouse.getMaterialType();
    }
}

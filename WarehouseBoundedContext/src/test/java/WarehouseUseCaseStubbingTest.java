import be.kdg.prog6.warehouseBoundedContext.core.WarehouseUseCaseImp;
import be.kdg.prog6.warehouseBoundedContext.domain.*;
import be.kdg.prog6.warehouseBoundedContext.port.in.WeighTruckOutCommand;
import be.kdg.prog6.warehouseBoundedContext.port.out.WarehouseSavePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WarehouseUseCaseStubbingTest {

    private WarehouseUseCaseImp warehouseUseCaseImp;
    private WarehouseLoadPortStub warehouseLoadPortStub;
    private AtomicInteger saveCounter;

    @BeforeEach
    void setUp() {
        warehouseLoadPortStub = new WarehouseLoadPortStub();

        saveCounter = new AtomicInteger();

        WarehouseSavePort warehouseSavePortStub = (warehouse, warehouseEvent) -> saveCounter.incrementAndGet();

        warehouseUseCaseImp = new WarehouseUseCaseImp(
                warehouseLoadPortStub,
                List.of(warehouseSavePortStub)
        );
    }

    @Test
    void truckOutViaStubs() {
        //arrange
        Warehouse warehouse = warehouseLoadPortStub.findBySellerIdAndMaterialType(
                warehouseLoadPortStub.getDefaultSellerId(),
                warehouseLoadPortStub.getDefaultMaterialType()
        );
        assertNotNull(warehouse, "Warehouse should not be null for the given SellerId and MaterialType.");

        double initialLoad = warehouse.getCurrentLoadOfWarehouse();

        double materialWeightForDelivery = 50.0;
        WeighTruckOutCommand command = new WeighTruckOutCommand(
                UUID.randomUUID(),
                "ABC123",
                warehouseLoadPortStub.getDefaultSellerId(),
                materialWeightForDelivery,
                warehouseLoadPortStub.getDefaultMaterialType(),
                LocalDateTime.now()
        );

        // Act:
        warehouseUseCaseImp.truckOut(command);

        // Assert
        assertEquals(1, saveCounter.intValue(), "WarehouseSavePort save method should have been called once.");
        List<WarehouseEvent> events = warehouse.getEventsWindow().getWarehouseEventList();
        WarehouseEvent lastEvent = events.get(events.size() - 1);

        assertEquals(EventType.DELIVER, lastEvent.getType(), "Event type should be DELIVER.");
        assertEquals(materialWeightForDelivery, lastEvent.getMaterialWeight(), "Event material weight should match the command's.");
        assertEquals(command.getWeighBridgeTicketId(), lastEvent.getWeighBridgeTicketId(), "Event weighBridgeTicketId should match the command's.");
        assertEquals(command.getMaterialType(), lastEvent.getMaterialType(), "Event material type should match the command's.");
        double expectedLoad = initialLoad + materialWeightForDelivery;
        assertEquals(expectedLoad, warehouse.getCurrentLoadOfWarehouse(),
                "Warehouse current load should be updated correctly after truckOut.");
    }
}

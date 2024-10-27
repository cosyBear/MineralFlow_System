import be.kdg.prog6.warehouseBoundedContext.core.WarehouseUseCaseImp;
import be.kdg.prog6.warehouseBoundedContext.domain.*;
import be.kdg.prog6.warehouseBoundedContext.port.in.WeighTruckOutCommand;
import be.kdg.prog6.warehouseBoundedContext.port.out.Warehouse.WarehouseLoadPort;
import be.kdg.prog6.warehouseBoundedContext.port.out.Warehouse.WarehouseSavePort;
import domain.MaterialType;
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
        // Initialize the stub for WarehouseLoadPort with sample data
        warehouseLoadPortStub = new WarehouseLoadPortStub();

        // Reset the counter before each test
        saveCounter = new AtomicInteger();

        // Stub for WarehouseSavePort to increment the counter each time save is called
        WarehouseSavePort warehouseSavePortStub = (warehouse, warehouseEvent) -> saveCounter.incrementAndGet();

        // Initialize WarehouseUseCaseImp with the stubbed ports
        warehouseUseCaseImp = new WarehouseUseCaseImp(
                warehouseLoadPortStub,
                List.of(warehouseSavePortStub)
        );
    }

    @Test
    void truckOutViaStubs() {
        // Replay all events in the default warehouse to get the initial load
        Warehouse warehouse = warehouseLoadPortStub.findBySellerIdAndMaterialType(
                warehouseLoadPortStub.getDefaultSellerId(),
                warehouseLoadPortStub.getDefaultMaterialType()
        );
        assertNotNull(warehouse, "Warehouse should not be null for the given SellerId and MaterialType.");

        // Calculate the initial load by summing the events' weights in the window
        double initialLoad = warehouse.getCurrentLoadOfWarehouse();

        // Create a test command for truckOut with matching SellerId and MaterialType as in the stub
        double materialWeightForDelivery = 50.0;
        WeighTruckOutCommand command = new WeighTruckOutCommand(
                UUID.randomUUID(),
                "ABC123",
                warehouseLoadPortStub.getDefaultSellerId(),
                materialWeightForDelivery,  // material weight for this test
                warehouseLoadPortStub.getDefaultMaterialType(),
                LocalDateTime.now()
        );

        // Act: Execute the truckOut functionality
        warehouseUseCaseImp.truckOut(command);

        // Assert: Verify that save was called exactly once
        assertEquals(1, saveCounter.intValue(), "WarehouseSavePort save method should have been called once.");

        // Check that a new DELIVER event was added
        List<WarehouseEvent> events = warehouse.getEventsWindow().getWarehouseEventList();
        WarehouseEvent lastEvent = events.get(events.size() - 1);  // Last event should be the one i just added

        assertEquals(EventType.DELIVER, lastEvent.getType(), "Event type should be DELIVER.");
        assertEquals(materialWeightForDelivery, lastEvent.getMaterialWeight(), "Event material weight should match the command's.");
        assertEquals(command.getWeighBridgeTicketId(), lastEvent.getWeighBridgeTicketId(), "Event weighBridgeTicketId should match the command's.");
        assertEquals(command.getMaterialType(), lastEvent.getMaterialType(), "Event material type should match the command's.");

        // Assert that the aggregate’s load reflects the replayed events (initial + new event weight)
        double expectedLoad = initialLoad + materialWeightForDelivery;
        assertEquals(expectedLoad, warehouse.getCurrentLoadOfWarehouse(),
                "Warehouse current load should be updated correctly after truckOut.");
    }
}

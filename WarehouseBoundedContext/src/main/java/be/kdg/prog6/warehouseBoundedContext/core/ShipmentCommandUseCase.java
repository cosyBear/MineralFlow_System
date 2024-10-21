package be.kdg.prog6.warehouseBoundedContext.core;

import be.kdg.prog6.warehouseBoundedContext.domain.*;
import be.kdg.prog6.warehouseBoundedContext.port.in.ShipmentCommand;
import be.kdg.prog6.warehouseBoundedContext.port.in.ShipmentOrderUseCase;
import be.kdg.prog6.warehouseBoundedContext.port.out.PurchaseOrderLoadPort;
import be.kdg.prog6.warehouseBoundedContext.port.out.PurchaseOrderSavePort;
import be.kdg.prog6.warehouseBoundedContext.port.out.Warehouse.WarehouseLoadPort;
import be.kdg.prog6.warehouseBoundedContext.port.out.Warehouse.WarehouseSavePort;
import be.kdg.prog6.warehouseBoundedContext.port.out.WaterSideEventPublisher;
import domain.MaterialType;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ShipmentCommandUseCase implements ShipmentOrderUseCase {

    //move all the commands to the port in folder.

    private PurchaseOrderSavePort purchaseOrderSavePort;
    private PurchaseOrderLoadPort purchaseOrderLoadPort;
    private WarehouseLoadPort warehouseLoadPort;
    private List<WarehouseSavePort> warehouseSavePort;
    private WaterSideEventPublisher waterSideEventPublisher;

    public ShipmentCommandUseCase(PurchaseOrderSavePort purchaseOrderSavePort, PurchaseOrderLoadPort purchaseOrderLoadPort, WarehouseLoadPort warehouseLoadPort,
                                  List<WarehouseSavePort> warehouseSavePort, WaterSideEventPublisher waterSideEventPublisher) {
        this.purchaseOrderSavePort = purchaseOrderSavePort;
        this.purchaseOrderLoadPort = purchaseOrderLoadPort;
        this.warehouseLoadPort = warehouseLoadPort;
        this.warehouseSavePort = warehouseSavePort;
        this.waterSideEventPublisher = waterSideEventPublisher;
    }

    @Override
    public void shipmentIn(ShipmentCommand command) {
        PurchaseOrder purchaseOrder = purchaseOrderLoadPort.loadById(command.purchaseOrder());

        Map<MaterialType, Double> requiredAmounts = purchaseOrder.getOrderLines().stream()
                .collect(Collectors.groupingBy(
                        PurchaseOrderLine::getMaterialType,
                        Collectors.summingDouble(PurchaseOrderLine::getQuantity)
                ));

        Warehouse warehouse = warehouseLoadPort.findBySellerId(purchaseOrder.getSellerId());

        List<WarehouseEvent> shippingEvents = warehouse.getEventsWindow().fulfillShippingOrder(requiredAmounts);// move to warehouse

        shippingEvents.forEach(event -> warehouse.getEventsWindow().addEvent(event));

        purchaseOrder.setStatus(PurchaseOrderStatus.fulfilled);
        // changet he name of the setstatus to set ful
        purchaseOrderSavePort.save(purchaseOrder);

        warehouseSavePort.forEach(savePort -> savePort.saveList(warehouse, shippingEvents));

        ShipmentCompletedEvent shipmentCompletedEvent = new ShipmentCompletedEvent(purchaseOrder.getPurchaseOrderId(),
                command.vesselNumber(), LocalDateTime.now());

        waterSideEventPublisher.ShipmentCompleted(shipmentCompletedEvent);

    }


}

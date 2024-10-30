package be.kdg.prog6.warehouseBoundedContext.core;

import be.kdg.prog6.warehouseBoundedContext.domain.*;
import be.kdg.prog6.warehouseBoundedContext.port.in.ShipmentCommand;
import be.kdg.prog6.warehouseBoundedContext.port.in.ShipmentOrderUseCase;
import be.kdg.prog6.warehouseBoundedContext.port.out.PurchaseOrderLoadPort;
import be.kdg.prog6.warehouseBoundedContext.port.out.PurchaseOrderSavePort;
import be.kdg.prog6.warehouseBoundedContext.port.in.ShipmentCompletedEvent;
import be.kdg.prog6.warehouseBoundedContext.port.out.WarehouseLoadPort;
import be.kdg.prog6.warehouseBoundedContext.port.out.WarehouseSavePort;
import be.kdg.prog6.warehouseBoundedContext.port.out.WaterSideEventPublisher;
import util.errorClasses.WarehouseNotFoundException;
import domain.MaterialType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ShipmentCommandUseCase implements ShipmentOrderUseCase {


    private final PurchaseOrderSavePort purchaseOrderSavePort;
    private final PurchaseOrderLoadPort purchaseOrderLoadPort;
    private final WarehouseLoadPort warehouseLoadPort;
    private final List<WarehouseSavePort> warehouseSavePort;
    private final WaterSideEventPublisher waterSideEventPublisher;

    public ShipmentCommandUseCase(PurchaseOrderSavePort purchaseOrderSavePort, PurchaseOrderLoadPort purchaseOrderLoadPort, WarehouseLoadPort warehouseLoadPort,
                                  List<WarehouseSavePort> warehouseSavePort, WaterSideEventPublisher waterSideEventPublisher) {
        this.purchaseOrderSavePort = purchaseOrderSavePort;
        this.purchaseOrderLoadPort = purchaseOrderLoadPort;
        this.warehouseLoadPort = warehouseLoadPort;
        this.warehouseSavePort = warehouseSavePort;
        this.waterSideEventPublisher = waterSideEventPublisher;
    }


    @Override
    @Transactional
    public void shipmentIn(ShipmentCommand command) {
        PurchaseOrder purchaseOrder = purchaseOrderLoadPort.loadById(command.purchaseOrder());
        Map<MaterialType, Double> requiredAmounts = purchaseOrder.getOrderLines().stream()
                .collect(Collectors.groupingBy(
                        PurchaseOrderLine::getMaterialType,
                        Collectors.summingDouble(PurchaseOrderLine::getQuantity)
                ));
        Map<MaterialType, Warehouse> warehouseMap = requiredAmounts.keySet().stream()
                .collect(Collectors.toMap(
                        materialType -> materialType,
                        materialType -> warehouseLoadPort.findBySellerIdAndMaterialType(
                                purchaseOrder.getSellerId(), materialType)
                ));
        List<WarehouseEvent> allShippingEvents = new ArrayList<>();
        for (Map.Entry<MaterialType, Double> entry : requiredAmounts.entrySet()) {
            MaterialType materialType = entry.getKey();
            double requiredAmount = entry.getValue();

            Warehouse warehouse = warehouseMap.get(materialType);
            if (warehouse != null) {
                List<WarehouseEvent> shippingEvents = warehouse.fulfillShippingOrder(materialType, requiredAmount);

                allShippingEvents.addAll(shippingEvents);

                warehouseSavePort.forEach(savePort -> savePort.saveList(warehouse, shippingEvents));
            } else {
                throw new WarehouseNotFoundException("No warehouse found for material type: " + materialType);
            }
        }
        purchaseOrder.updateStatus(PurchaseOrderStatus.fulfilled);
        purchaseOrderSavePort.save(purchaseOrder);

        ShipmentCompletedEvent shipmentCompletedEvent = new ShipmentCompletedEvent(
                purchaseOrder.getPurchaseOrderId(),
                command.vesselNumber(),
                LocalDateTime.now()
        );
        waterSideEventPublisher.ShipmentCompleted(shipmentCompletedEvent);
    }
// change the domain enum dont use it in my jpa


}

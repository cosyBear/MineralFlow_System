package be.kdg.prog6.warehouseBoundedContext.core;

import be.kdg.prog6.warehouseBoundedContext.domain.*;
import be.kdg.prog6.warehouseBoundedContext.port.in.ShipmentCommand;
import be.kdg.prog6.warehouseBoundedContext.port.in.ShipmentOrderUseCase;
import be.kdg.prog6.warehouseBoundedContext.port.out.PurchaseOrderLoadPort;
import be.kdg.prog6.warehouseBoundedContext.port.out.PurchaseOrderSavePort;
import be.kdg.prog6.warehouseBoundedContext.port.out.Warehouse.WarehouseLoadPort;
import be.kdg.prog6.warehouseBoundedContext.port.out.Warehouse.WarehouseSavePort;
import domain.MaterialType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentCommandUseCase implements ShipmentOrderUseCase {

    //move all the commands to the port in folder.

   private PurchaseOrderSavePort purchaseOrderSavePort;
   private PurchaseOrderLoadPort purchaseOrderLoadPort;
   private WarehouseLoadPort warehouseLoadPort;
   private List<WarehouseSavePort> warehouseSavePort;
    public ShipmentCommandUseCase(PurchaseOrderSavePort purchaseOrderSavePort, PurchaseOrderLoadPort purchaseOrderLoadPort , WarehouseLoadPort warehouseLoadPort,
                                  List<WarehouseSavePort> warehouseSavePort) {
        this.purchaseOrderSavePort = purchaseOrderSavePort;
        this.purchaseOrderLoadPort = purchaseOrderLoadPort;
        this.warehouseLoadPort = warehouseLoadPort;
        this.warehouseSavePort = warehouseSavePort;
    }

    @Override
    public void shipmentIn(ShipmentCommand command) {
        PurchaseOrder purchaseOrder = purchaseOrderLoadPort.loadById(command.purchaseOrder());
        double totalRequiredAmount = purchaseOrder.getOrderLines().stream()
                .mapToDouble(PurchaseOrderLine::getQuantity)
                .sum();

        MaterialType materialType = purchaseOrder.getOrderLines().get(0).getMaterialType();

        Warehouse warehouse = warehouseLoadPort.findBySellerIdAndMaterialType( purchaseOrder.getSellerId() ,materialType);

        List<WarehouseEvent> shippingEvents = warehouse.getEventsWindow().fulfillShippingOrder(totalRequiredAmount);

        shippingEvents.forEach(event -> warehouse.getEventsWindow().addEvent(event));

        purchaseOrder.setStatus(PurchaseOrderStatus.fulfilled);
        purchaseOrderSavePort.save(purchaseOrder);

        warehouseSavePort.forEach(savePort -> savePort.saveList(warehouse, shippingEvents));


    }

}

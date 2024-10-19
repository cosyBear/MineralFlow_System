package be.kdg.prog6.watersideboundedcontext.core;

import be.kdg.prog6.watersideboundedcontext.domain.ShipmentOrder;
import be.kdg.prog6.watersideboundedcontext.port.in.ShipmentOrderCommand;
import be.kdg.prog6.watersideboundedcontext.port.in.ShipmentOrderUseCase;
import be.kdg.prog6.watersideboundedcontext.port.out.ShipmentOrderLoadPort;
import be.kdg.prog6.watersideboundedcontext.port.out.ShipmentOrderSavePort;
import jakarta.transaction.Transactional;

public class ShipmentUseCaseImp implements ShipmentOrderUseCase {

    private final ShipmentOrderLoadPort shipmentOrderLoadPort;
    private final ShipmentOrderSavePort shipmentOrderSavePort;

    public ShipmentUseCaseImp(ShipmentOrderLoadPort shipmentOrderLoadPort, ShipmentOrderSavePort shipmentOrderSavePort) {
        this.shipmentOrderLoadPort = shipmentOrderLoadPort;
        this.shipmentOrderSavePort = shipmentOrderSavePort;
    }


    @Override
    @Transactional
    public void requestMaterial(ShipmentOrderCommand order) {
        ShipmentOrder shipmentOrder  = shipmentOrderLoadPort.loadShipmentOrderById(order.purchaseOrder());
        shipmentOrder.performInspectionOperation(order.purchaseOrder());




    }
}

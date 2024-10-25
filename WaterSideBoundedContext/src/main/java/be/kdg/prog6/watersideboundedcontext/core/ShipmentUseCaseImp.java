package be.kdg.prog6.watersideboundedcontext.core;
import be.kdg.prog6.watersideboundedcontext.port.out.RequestMaterialEvent;
import be.kdg.prog6.watersideboundedcontext.port.in.ShipmentCompletedCommand;
import be.kdg.prog6.watersideboundedcontext.domain.ShipmentOrder;
import be.kdg.prog6.watersideboundedcontext.port.in.ShipmentOrderCommand;
import be.kdg.prog6.watersideboundedcontext.port.in.ShipmentOrderUseCase;
import be.kdg.prog6.watersideboundedcontext.port.out.ShipmentOrderEventPublisher;
import be.kdg.prog6.watersideboundedcontext.port.out.ShipmentOrderLoadPort;
import be.kdg.prog6.watersideboundedcontext.port.out.ShipmentOrderSavePort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ShipmentUseCaseImp implements ShipmentOrderUseCase {

    private final ShipmentOrderLoadPort shipmentOrderLoadPort;
    private final ShipmentOrderSavePort shipmentOrderSavePort;

    private final ShipmentOrderEventPublisher shipmentOrderEventPublisher;

    public ShipmentUseCaseImp(ShipmentOrderLoadPort shipmentOrderLoadPort, ShipmentOrderSavePort shipmentOrderSavePort, ShipmentOrderEventPublisher shipmentOrderEventPublisher) {
        this.shipmentOrderLoadPort = shipmentOrderLoadPort;
        this.shipmentOrderSavePort = shipmentOrderSavePort;
        this.shipmentOrderEventPublisher = shipmentOrderEventPublisher;
    }

    @Override
    @Transactional
    public void requestMaterial(ShipmentOrderCommand order) {
        ShipmentOrder shipmentOrder = shipmentOrderLoadPort.loadShipmentOrderById(order.shipmentOrder());
        shipmentOrder.performInspectionOperation(order.purchaseOrder());
        RequestMaterialEvent event = new RequestMaterialEvent(shipmentOrder.getPurchaseOrder(), shipmentOrder.getVesselNumber(), shipmentOrder.getArrivalTime());
        shipmentOrderSavePort.Save(shipmentOrder);
        shipmentOrderEventPublisher.requestMaterialEvent(event);

    }

    @Override
    @Transactional
    public boolean shipDeparture(ShipmentCompletedCommand shipmentOrder) {

        ShipmentOrder order = shipmentOrderLoadPort.loadByPurchaseOrderId(shipmentOrder.purchaseOrderId());
        order.completeBunkeringOperation(shipmentOrder.departureTime());

        if (order.canShipLeave()) {
            shipmentOrderSavePort.Save(order);
            return true;
        } else
            return false;

    }
}

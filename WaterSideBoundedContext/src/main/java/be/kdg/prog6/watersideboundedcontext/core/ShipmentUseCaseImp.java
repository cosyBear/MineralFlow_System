package be.kdg.prog6.watersideboundedcontext.core;
import be.kdg.prog6.watersideboundedcontext.domain.ShipmentOrderStatus;
import be.kdg.prog6.watersideboundedcontext.port.in.RequestMaterialEvent;
import be.kdg.prog6.watersideboundedcontext.port.in.ShipmentCompletedCommand;
import be.kdg.prog6.watersideboundedcontext.domain.ShipmentOrder;
import be.kdg.prog6.watersideboundedcontext.port.in.RequestMaterialCommand;
import be.kdg.prog6.watersideboundedcontext.port.in.ShipmentOrderUseCase;
import be.kdg.prog6.watersideboundedcontext.port.out.ShipmentOrderEventPublisher;
import be.kdg.prog6.watersideboundedcontext.port.out.ShipmentOrderLoadPort;
import be.kdg.prog6.watersideboundedcontext.port.out.ShipmentOrderSavePort;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ShipmentUseCaseImp implements ShipmentOrderUseCase {

    private final ShipmentOrderLoadPort shipmentOrderLoadPort;
    private final ShipmentOrderSavePort shipmentOrderSavePort;

    private final ShipmentOrderEventPublisher shipmentOrderEventPublisher;
    private static final Logger logger = LogManager.getLogger(ShipmentUseCaseImp.class);

    public ShipmentUseCaseImp(ShipmentOrderLoadPort shipmentOrderLoadPort, ShipmentOrderSavePort shipmentOrderSavePort, ShipmentOrderEventPublisher shipmentOrderEventPublisher) {
        this.shipmentOrderLoadPort = shipmentOrderLoadPort;
        this.shipmentOrderSavePort = shipmentOrderSavePort;
        this.shipmentOrderEventPublisher = shipmentOrderEventPublisher;
    }

    @Override
    @Transactional
    public void requestMaterial(RequestMaterialCommand order) {
        ShipmentOrder shipmentOrder = shipmentOrderLoadPort.loadShipmentOrderById(order.shipmentOrder());
        shipmentOrder.insertActualArrivalTime(order.arrivalTime());
        shipmentOrder.updateShipStatus(ShipmentOrderStatus.LOADING_IN_PROGRESS);
        RequestMaterialEvent event = new RequestMaterialEvent(shipmentOrder.getPurchaseOrder(), shipmentOrder.getVesselNumber(), shipmentOrder.getActualArrivalTime());
        shipmentOrderSavePort.Save(shipmentOrder);
        shipmentOrderEventPublisher.requestMaterialEvent(event);
        logger.info("the ship is asking to for the material. ");

    }

    @Override
    @Transactional
    public void shipDeparture(ShipmentCompletedCommand shipmentOrder) {
        ShipmentOrder order = shipmentOrderLoadPort.loadByPurchaseOrderId(shipmentOrder.purchaseOrderId());
        order.updateShipStatus(ShipmentOrderStatus.AWAITING_INSPECTION_AND_BUNKERING);
        shipmentOrderSavePort.Save(order);
        logger.info("the ship is loaded with the material AWAITING  INSPECTION AND BUNKERING ");
    }
}

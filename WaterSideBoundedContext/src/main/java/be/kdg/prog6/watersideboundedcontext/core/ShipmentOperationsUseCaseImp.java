package be.kdg.prog6.watersideboundedcontext.core;

import be.kdg.prog6.watersideboundedcontext.adapters.in.ShipmentCompletedAmqpAdapter;
import be.kdg.prog6.watersideboundedcontext.domain.ShipmentOrder;
import be.kdg.prog6.watersideboundedcontext.domain.ShipmentOrderStatus;
import be.kdg.prog6.watersideboundedcontext.port.in.OperationRequestCommand;
import be.kdg.prog6.watersideboundedcontext.port.in.ShipmentOperationsPort;
import be.kdg.prog6.watersideboundedcontext.port.out.ShipmentOrderLoadPort;
import be.kdg.prog6.watersideboundedcontext.port.out.ShipmentOrderSavePort;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;
@Component
public class ShipmentOperationsUseCaseImp implements ShipmentOperationsPort {

    private final ShipmentOrderLoadPort shipmentOrderLoadPort;
    private final ShipmentOrderSavePort shipmentOrderSavePort;
    private static final Logger logger = LogManager.getLogger(ShipmentOperationsUseCaseImp.class);

    public ShipmentOperationsUseCaseImp(ShipmentOrderLoadPort shipmentOrderLoadPort, ShipmentOrderSavePort shipmentOrderSavePort) {
        this.shipmentOrderLoadPort = shipmentOrderLoadPort;
        this.shipmentOrderSavePort = shipmentOrderSavePort;
    }


    @Override
    public boolean performBunkeringOperationAndInspectionOperation(OperationRequestCommand command) {
        ShipmentOrder shipmentOrder = shipmentOrderLoadPort.loadShipmentOrderById(command.shipmentOrderId());
        shipmentOrder.performInspectionOperation(command.inspectionTimeOfSigning(), command.inspectorSignature());
        shipmentOrder.performBunkeringOperation(command.bunkeringTime());
        shipmentOrder.updateShipStatus(ShipmentOrderStatus.PERFORMING_INSPECTION_AND_BUNKERING);
        shipmentOrderSavePort.Save(shipmentOrder);
        logger.info(" Bunkering And InspectionOperation are done");
        return true;
    }

    @Override
    public boolean isDepartureAuthorized(UUID shipmentOrderId ) {
        ShipmentOrder shipmentOrder = shipmentOrderLoadPort.loadShipmentOrderById(shipmentOrderId);
        shipmentOrder.updateShipStatus(ShipmentOrderStatus.COMPLETED);
        shipmentOrder.insertActualDepartureTime(LocalDateTime.now());
        shipmentOrderSavePort.Save(shipmentOrder);
        logger.info(" the Ship is allowed to LEAVE. everything is in order........");
        return shipmentOrder.isShipAllowedToLeave();

    }
}

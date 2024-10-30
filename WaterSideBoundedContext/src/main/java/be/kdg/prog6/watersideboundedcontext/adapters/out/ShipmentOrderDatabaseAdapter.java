package be.kdg.prog6.watersideboundedcontext.adapters.out;

import be.kdg.prog6.watersideboundedcontext.adapters.out.Entity.BunkeringOperationEntity;
import be.kdg.prog6.watersideboundedcontext.adapters.out.Entity.InspectionOperationEntity;
import be.kdg.prog6.watersideboundedcontext.adapters.out.Entity.ShipmentOrderEntity;
import be.kdg.prog6.watersideboundedcontext.adapters.out.repo.ShipmentOrderRepository;
import be.kdg.prog6.watersideboundedcontext.domain.BunkeringOperation;
import be.kdg.prog6.watersideboundedcontext.domain.InspectionOperation;
import be.kdg.prog6.watersideboundedcontext.domain.ShipmentOrder;
import be.kdg.prog6.watersideboundedcontext.port.out.ShipmentOrderLoadPort;
import be.kdg.prog6.watersideboundedcontext.port.out.ShipmentOrderSavePort;
import util.errorClasses.NoShipmentOrderException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import util.errorClasses.ShipmentOrderNotFoundException;

import java.util.UUID;

@Component
public class ShipmentOrderDatabaseAdapter implements ShipmentOrderSavePort, ShipmentOrderLoadPort {

    @PersistenceContext
    private EntityManager entityManager;

    private final ShipmentOrderRepository shipmentOrderRepository;

    public ShipmentOrderDatabaseAdapter(ShipmentOrderRepository shipmentOrderRepository) {
        this.shipmentOrderRepository = shipmentOrderRepository;
    }

    @Override
    public void Save(ShipmentOrder order) {
        ShipmentOrderEntity shipmentOrder = mapToEntity(order);
        shipmentOrderRepository.save(shipmentOrder);
    }

    @Override
    public ShipmentOrder loadShipmentOrderById(UUID shipmentOrderId) {
        return mapToDomain(shipmentOrderRepository.findById(shipmentOrderId)
                .orElseThrow(() -> new NoShipmentOrderException("No shipment order found with id " + shipmentOrderId)));
    }

    @Override
    public ShipmentOrder loadByPurchaseOrderId(UUID purchaseOrderId) {
        ShipmentOrderEntity shipmentOrderEntity = shipmentOrderRepository.findByPurchaseOrder(purchaseOrderId);
        if (shipmentOrderEntity == null) {
            throw new ShipmentOrderNotFoundException("Shipment order not found for purchase order ID: " + purchaseOrderId);
        }
        return mapToDomain(shipmentOrderEntity);
    }

    private ShipmentOrderEntity mapToEntity(ShipmentOrder order) {
        return new ShipmentOrderEntity(
                order.getPurchaseOrder(),
                order.getExpectedArrivalTime(),
                order.getExpectedDepartureTime(),
                order.getVesselNumber(),
                order.getInspectionOperation() != null
                        ? new InspectionOperationEntity(order.getInspectionOperation().timeOfSigning(), order.getInspectionOperation().signature())
                        : null,
                order.getBunkeringOperation() != null
                        ? new BunkeringOperationEntity(order.getBunkeringOperation().bunkeringTime())
                        : null,
                order.getShipmentOrderId(),
                order.getActualArrivalTime(),
                order.getActualDepartureTime(),
                order.getStatus()
        );
    }

    private ShipmentOrder mapToDomain(ShipmentOrderEntity shipmentOrder) {
        InspectionOperation inspectionOperation = shipmentOrder.getInspectionOperation() != null
                ? new InspectionOperation(
                shipmentOrder.getInspectionOperation().getInspectionDate(),
                shipmentOrder.getInspectionOperation().getInspectorSignature())
                : null;

        BunkeringOperation bunkeringOperation = shipmentOrder.getBunkeringOperation() != null
                ? new BunkeringOperation(shipmentOrder.getBunkeringOperation().getBunkeringTime())
                : null;

        return new ShipmentOrder(
                inspectionOperation,
                bunkeringOperation,
                shipmentOrder.getExpectedDepartureTime(),
                shipmentOrder.getExpectedArrivalTime(),
                shipmentOrder.getVesselId(),
                shipmentOrder.getPurchaseOrder(),
                shipmentOrder.getShipmentOrderId(),
                shipmentOrder.getActualArrivalTime(),
                shipmentOrder.getActualDepartureTime(),
                shipmentOrder.getStatus()
        );
    }
}

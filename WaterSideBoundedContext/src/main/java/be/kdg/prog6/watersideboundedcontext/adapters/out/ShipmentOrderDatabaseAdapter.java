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
import be.kdg.prog6.watersideboundedcontext.util.NoShipmentOrderException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

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
        return mapToDomain(shipmentOrderRepository.findById(shipmentOrderId).orElseThrow(() -> {
            throw new NoShipmentOrderException("no shipment order found with id " + shipmentOrderId);
        }));
    }

    @Override
    public ShipmentOrder loadByPurchaseOrderId(UUID purchaseOrderId) {
        return mapToDomain(shipmentOrderRepository.findByPurchaseOrder(purchaseOrderId));
    }


    private ShipmentOrderEntity mapToEntity(ShipmentOrder order) {
        return new ShipmentOrderEntity(
                order.getPurchaseOrder(),
                order.getDepartureTime(),
                order.getArrivalTime(),
                order.getVesselNumber(),
                new InspectionOperationEntity(order.getInspectionOperation().timeOfSigning(), order.getInspectionOperation().signature()),
                new BunkeringOperationEntity(order.getBunkeringOperation().bunkeringTime()),order.getShipmentOrderId()
        );
    }

    private ShipmentOrder mapToDomain(ShipmentOrderEntity shipmentOrder) {
        InspectionOperation inspectionOperation = (shipmentOrder.getInspectionOperation() == null)
                ? new InspectionOperation()
                : new InspectionOperation(shipmentOrder.getInspectionOperation().getInspectionDate(), shipmentOrder.getInspectionOperation().getInspectorSignature());

        BunkeringOperation bunkeringOperation = (shipmentOrder.getBunkeringOperation() == null)
                ? new BunkeringOperation()
                : new BunkeringOperation(shipmentOrder.getBunkeringOperation().getBunkeringTime());

        return new ShipmentOrder(
                inspectionOperation,
                bunkeringOperation,
                shipmentOrder.getDepartureTime(),
                shipmentOrder.getArrivalTime(),
                shipmentOrder.getVesselId(),
                shipmentOrder.getPurchaseOrder(),
                shipmentOrder.getShipmentOrderId());
    }



}

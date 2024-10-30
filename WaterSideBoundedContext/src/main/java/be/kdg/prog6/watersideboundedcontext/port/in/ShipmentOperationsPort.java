package be.kdg.prog6.watersideboundedcontext.port.in;

import java.util.UUID;

public interface ShipmentOperationsPort {

    boolean performBunkeringOperationAndInspectionOperation(OperationRequestCommand  command);

    boolean isDepartureAuthorized(UUID shipmentOrderId);
}

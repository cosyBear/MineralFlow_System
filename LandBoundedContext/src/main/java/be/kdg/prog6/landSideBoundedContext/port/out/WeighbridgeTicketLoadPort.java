package be.kdg.prog6.landSideBoundedContext.port.out;

import be.kdg.prog6.landSideBoundedContext.domain.MaterialType;
import be.kdg.prog6.landSideBoundedContext.domain.WeighbridgeTicket;

import java.util.UUID;

public interface WeighbridgeTicketLoadPort {

    WeighbridgeTicket loadById(UUID id) ;

    WeighbridgeTicket loadBySellerIDAndMaterialType(UUID sellerId , MaterialType materialType);

}

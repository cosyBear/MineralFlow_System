package be.kdg.prog6.landSideBoundedContext.port.out;

import be.kdg.prog6.landSideBoundedContext.domain.WeighbridgeTicket;
import domain.MaterialType;

import java.util.UUID;
public interface WeighbridgeTicketLoadPort {

    WeighbridgeTicket loadById(UUID id) ;


}

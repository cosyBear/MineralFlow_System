package be.kdg.prog6.LandSideBoundedContext.port.out;

import be.kdg.prog6.LandSideBoundedContext.domain.WeighbridgeTicket;

import java.util.UUID;

public interface WeighbridgeTicketLoadPort {

    WeighbridgeTicket loadById(UUID id) throws Exception;
}

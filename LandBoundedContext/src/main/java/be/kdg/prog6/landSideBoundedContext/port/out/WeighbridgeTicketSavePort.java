package be.kdg.prog6.landSideBoundedContext.port.out;

import be.kdg.prog6.landSideBoundedContext.domain.WeighbridgeTicket;
@FunctionalInterface

public interface WeighbridgeTicketSavePort {


    void save(WeighbridgeTicket weighbridgeTicket);



}

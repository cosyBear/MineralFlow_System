package be.kdg.prog6.landSideBoundedContext.port.in;

import be.kdg.prog6.landSideBoundedContext.domain.Appointment;

public interface GateUseCase {


    Appointment GateSecurity(GateCommand gateCommand);

}

package be.kdg.prog6.LandSideBoundedContext.port.in;

import be.kdg.prog6.LandSideBoundedContext.domain.Appointment;

public interface GateUseCase {


    Appointment GateSecurity(GateCommand gateCommand);

}

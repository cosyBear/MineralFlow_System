package be.kdg.prog6.LandSideBoundedContext.port.in;

import be.kdg.prog6.LandSideBoundedContext.domain.LicensePlate;

public interface GateCommandPort {


    Boolean GateSecurity(GateCommand gateCommand);

}

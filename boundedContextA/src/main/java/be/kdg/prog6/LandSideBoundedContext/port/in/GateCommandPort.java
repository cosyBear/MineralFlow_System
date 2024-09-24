package be.kdg.prog6.LandSideBoundedContext.port.in;

import be.kdg.prog6.LandSideBoundedContext.domain.Appointment;
import be.kdg.prog6.LandSideBoundedContext.domain.LicensePlate;

public interface GateCommandPort {


    Appointment GateSecurity(GateCommand gateCommand);

}

package be.kdg.prog6.LandSideBoundedContext.core;
import be.kdg.prog6.LandSideBoundedContext.domain.Calendar;
import be.kdg.prog6.LandSideBoundedContext.port.in.GateCommand;
import be.kdg.prog6.LandSideBoundedContext.port.in.GateCommandPort;
import be.kdg.prog6.LandSideBoundedContext.port.out.CalendarLoadPort;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class GateUseCaseImp implements GateCommandPort {

    private CalendarLoadPort calendarLoadPort;

    private static final Logger logger = LogManager.getLogger(GateCommandPort.class);

    public GateUseCaseImp(CalendarLoadPort calendarLoadPort) {
        this.calendarLoadPort = calendarLoadPort;
    }

    @Override
    public Boolean GateSecurity(GateCommand gateCommand) {
        logger.info("check licensePlate of the truck ... ");
        Calendar calendar = calendarLoadPort.loadAppointmentsByDate(gateCommand.localDate().toLocalDate());
        if (calendar.isTruckOnTime(gateCommand.localDate())) {
            if (calendar.PassGate(gateCommand.licensePlate()))
            {
                logger.info("you may enter ... ");
                return true;
            }
            else{
                logger.info("you do not enter ... ");
                return false;
            }
        }else {
            logger.info("Pookie you cant Enter you have no Appointment ... ");
            return false;
        }
    }
}

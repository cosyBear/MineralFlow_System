package be.kdg.prog6.LandSideBoundedContext.core;
import be.kdg.prog6.LandSideBoundedContext.domain.Appointment;
import be.kdg.prog6.LandSideBoundedContext.domain.Calendar;
import be.kdg.prog6.LandSideBoundedContext.port.in.GateCommand;
import be.kdg.prog6.LandSideBoundedContext.port.in.GateUseCase;
import be.kdg.prog6.LandSideBoundedContext.port.out.CalendarLoadPort;
import be.kdg.prog6.LandSideBoundedContext.util.errorClasses.AppointmentDontExist;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class GateUseCaseImp implements GateUseCase {

    private CalendarLoadPort calendarLoadPort;

    private static final Logger logger = LogManager.getLogger(GateUseCase.class);

    public GateUseCaseImp(CalendarLoadPort calendarLoadPort) {
        this.calendarLoadPort = calendarLoadPort;
    }

    @Override
    public Appointment GateSecurity(GateCommand gateCommand) {
        logger.info("check licensePlate of the truck ... ");
        Calendar calendar = calendarLoadPort.loadAppointmentsByDate(gateCommand.localDate().toLocalDate());
        try{
            return  calendar.isTruckOnTime(gateCommand.localDate()).get();

        }catch (NoSuchElementException ex){

            throw new AppointmentDontExist("Appointment not found you may not Enter ");
        }

    }


}

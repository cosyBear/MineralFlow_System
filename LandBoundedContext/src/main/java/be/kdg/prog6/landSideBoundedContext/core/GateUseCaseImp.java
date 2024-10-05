//package be.kdg.prog6.landSideBoundedContext.core;
//import be.kdg.prog6.landSideBoundedContext.domain.Appointment;
//import be.kdg.prog6.landSideBoundedContext.domain.DayCalendar;
//import be.kdg.prog6.landSideBoundedContext.port.in.GateCommand;
//import be.kdg.prog6.landSideBoundedContext.port.in.GateUseCase;
//import be.kdg.prog6.landSideBoundedContext.port.out.CalendarLoadPort;
//import be.kdg.prog6.landSideBoundedContext.util.errorClasses.AppointmentDontExist;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.stereotype.Service;
//
//import java.util.NoSuchElementException;
//
//@Service
//public class GateUseCaseImp implements GateUseCase {
//
//    private CalendarLoadPort calendarLoadPort;
//
//    private static final Logger logger = LogManager.getLogger(GateUseCase.class);
//
//    public GateUseCaseImp(CalendarLoadPort calendarLoadPort) {
//        this.calendarLoadPort = calendarLoadPort;
//    }
//
//    @Override
//    public Appointment GateSecurity(GateCommand gateCommand) {
//        logger.info("check licensePlate of the truck ... ");
//        DayCalendar dayCalendar = calendarLoadPort.loadAppointmentsByDate(gateCommand.localDate().toLocalDate());
//        try{
//
//            return  dayCalendar.isTruckOnTime(gateCommand.localDate()).get();
//
//        }catch (NoSuchElementException ex){
//
//            throw new AppointmentDontExist("Appointment not found you may not Enter ");
//        }
//
//    }
//
//
//}
//

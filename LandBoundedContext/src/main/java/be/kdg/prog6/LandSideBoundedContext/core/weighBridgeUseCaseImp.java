package be.kdg.prog6.LandSideBoundedContext.core;

import be.kdg.prog6.LandSideBoundedContext.domain.DayCalendar;
import be.kdg.prog6.LandSideBoundedContext.domain.WeighBridgeTicketId;
import be.kdg.prog6.LandSideBoundedContext.domain.WeighInEvent;
import be.kdg.prog6.LandSideBoundedContext.domain.WeighbridgeTicket;
import be.kdg.prog6.LandSideBoundedContext.port.in.WeighBridgeUseCase;
import be.kdg.prog6.LandSideBoundedContext.port.in.weighTruckInCommand;
import be.kdg.prog6.LandSideBoundedContext.port.out.*;
import be.kdg.prog6.LandSideBoundedContext.util.errorClasses.AppointmentDontExist;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class weighBridgeUseCaseImp implements WeighBridgeUseCase {

    private static final Logger logger = LogManager.getLogger(weighBridgeUseCaseImp.class);

    private final WeighBridgeEventPublisher eventPublisher;
    private final CalendarLoadPort calendarLoadPort;
    private final  AppointmentSavePort appointmentSavePort;
    private  final WeighbridgeTicketLoadPort weighbridgeTicketLoadPort;
    private final WeighbridgeTicketSavePort weighbridgeTicketSavePort;

    public weighBridgeUseCaseImp(WeighBridgeEventPublisher eventPublisher, CalendarLoadPort calendarLoadPort, AppointmentSavePort appointmentSavePort, WeighbridgeTicketLoadPort weighbridgeTicketLoadPort, WeighbridgeTicketSavePort weighbridgeTicketSavePort) {
        this.eventPublisher = eventPublisher;
        this.calendarLoadPort = calendarLoadPort;
        this.appointmentSavePort = appointmentSavePort;
        this.weighbridgeTicketLoadPort = weighbridgeTicketLoadPort;
        this.weighbridgeTicketSavePort = weighbridgeTicketSavePort;
    }

        //TODO   Change the licensePlate from string to UUID
    @Override
    public void weighTruckIn(weighTruckInCommand command) {

        logger.info("check licensePlate of the truck ... ");
        WeighbridgeTicket weighbridgeTicket;
        WeighInEvent weighInEvent;
        try{
            DayCalendar dayCalendar = calendarLoadPort.loadAppointmentsByDate(command.weighInTime().toLocalDate());

            // check if the truck on time
            if(dayCalendar.allowTruckToEnter(command.licensePlate(), command.weighInTime())){
                logger.info(" the Truck is on time the gate is open.....");
                weighbridgeTicket = new WeighbridgeTicket( new WeighBridgeTicketId(UUID.randomUUID()), command.licensePlate() , command.sellerId() , command.startWeight() , 0 , command.materialType(), command.weighInTime());
                weighInEvent   = new WeighInEvent( weighbridgeTicket.getWeighBridgeTicketId() , weighbridgeTicket.getLicensePlate() ,  weighbridgeTicket.getSellerId(), weighbridgeTicket.getStartWeight() , weighbridgeTicket.getMaterialType() , weighbridgeTicket.getStartTime());
                logger.info(" WeighbridgeTicket (WBT) has be created.....");
                logger.info(" weighInEvent has be created.....");
                weighbridgeTicketSavePort.saveWeighbridgeTicket(weighbridgeTicket);
                eventPublisher.publishTruckWeightedIn(weighInEvent);
            }
        }catch (NoSuchElementException ex){

            throw new AppointmentDontExist("Appointment not found you may not Enter ");
        }


    }

    @Override
    public void weighTruckOut(weighTruckInCommand command) {

    }
}

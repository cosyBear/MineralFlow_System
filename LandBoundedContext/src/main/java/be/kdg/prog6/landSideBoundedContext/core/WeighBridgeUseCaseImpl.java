package be.kdg.prog6.landSideBoundedContext.core;

import be.kdg.prog6.landSideBoundedContext.domain.*;
import be.kdg.prog6.landSideBoundedContext.domain.Id.WeighBridgeTicketId;
import be.kdg.prog6.landSideBoundedContext.domain.WeighInEvent;
import be.kdg.prog6.landSideBoundedContext.domain.WeighOutEvent;
import be.kdg.prog6.landSideBoundedContext.port.in.WeighBridgeUseCase;
import be.kdg.prog6.landSideBoundedContext.port.in.WeighTruckInCommand;
import be.kdg.prog6.landSideBoundedContext.port.in.weighTruckOutCommand;
import be.kdg.prog6.landSideBoundedContext.port.out.*;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WeighBridgeUseCaseImpl implements WeighBridgeUseCase {

    private static final Logger logger = LogManager.getLogger(WeighBridgeUseCaseImpl.class);

    private final WeighBridgeEventPublisher eventPublisher;
    private final CalendarLoadPort calendarLoadPort;
    private AppointmentSavePort appointmentSavePort;
    private final WeighbridgeTicketLoadPort weighbridgeTicketLoadPort;
    private final WeighbridgeTicketSavePort weighbridgeTicketSavePort;
    private final WarehouseLoadPort warehouseLoadPort;
    private final WarehouseSavePort warehouseSavePort;
    private CalendarSavePort calendarSavePort;


    public WeighBridgeUseCaseImpl(WeighBridgeEventPublisher eventPublisher, CalendarLoadPort calendarLoadPort, AppointmentSavePort appointmentSavePort, WeighbridgeTicketLoadPort weighbridgeTicketLoadPort, WeighbridgeTicketSavePort weighbridgeTicketSavePort, WarehouseLoadPort warehouseLoadPort, WarehouseSavePort warehouseSavePort, CalendarSavePort calendarSavePort) {
        this.eventPublisher = eventPublisher;
        this.calendarLoadPort = calendarLoadPort;
        this.weighbridgeTicketLoadPort = weighbridgeTicketLoadPort;
        this.weighbridgeTicketSavePort = weighbridgeTicketSavePort;
        this.warehouseLoadPort = warehouseLoadPort;
        this.warehouseSavePort = warehouseSavePort;
        this.appointmentSavePort = appointmentSavePort;
        this.calendarSavePort = calendarSavePort;
    }


    @Override
    @Transactional
    public void weighTruckIn(WeighTruckInCommand command) {

        logger.info("check licensePlate of the truck ... ");

        DayCalendar dayCalendar = calendarLoadPort.loadAppointmentsByDate(command.weighInTime().toLocalDate());


        if (dayCalendar.truckEnters(command.licensePlate(), command.weighInTime())) {
            logger.info(" the Truck is on time the gate is open.....");
            WeighbridgeTicket weighbridgeTicket = new WeighbridgeTicket(new WeighBridgeTicketId(UUID.randomUUID()), command.licensePlate(), command.sellerId(), command.startWeight(), 0, command.materialType(), command.weighInTime());
            WeighInEvent weighInEvent = new WeighInEvent(weighbridgeTicket.getWeighBridgeTicketId().id(), command.licensePlate().licensePlate(), command.sellerId().id(), command.startWeight(), command.materialType(), command.weighInTime());
            logger.info(" WeighbridgeTicket (WBT) has be created.....");
            logger.info(" weighInEvent has be created.....");

            weighbridgeTicketSavePort.save(weighbridgeTicket);
            eventPublisher.publishTruckWeightedIn(weighInEvent);
            calendarSavePort.saveDayCalendar(dayCalendar);
        }
    }


    @Override
    @Transactional
    public void weighTruckOut(weighTruckOutCommand command) {
        WeighbridgeTicket bridgeTicket = weighbridgeTicketLoadPort.loadById(command.WeighBridgeTicketId().id());

        bridgeTicket.truckWeighsOut(command.weighOutTime(), command.endWeight());

        WeighOutEvent weighEvent = new WeighOutEvent(bridgeTicket.getWeighBridgeTicketId().id(), command.licensePlate().toString(), command.sellerId().id(), command.endWeight(), command.materialType(), command.weighOutTime());

        eventPublisher.publishTruckWeighedOut(weighEvent);

        DayCalendar calendar = calendarLoadPort.loadAppointmentsByDate(bridgeTicket.getStartTime().toLocalDate());

        Appointment appointment = calendar.appointmentDone(command.sellerId(), command.licensePlate());

        calendarSavePort.saveDayCalendar(calendar);
        weighbridgeTicketSavePort.save(bridgeTicket);

    }


}

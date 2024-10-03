package be.kdg.prog6.landSideBoundedContext.core;

import be.kdg.prog6.landSideBoundedContext.domain.*;
import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import be.kdg.prog6.landSideBoundedContext.domain.Id.WeighBridgeTicketId;
import be.kdg.prog6.landSideBoundedContext.port.in.WeighBridgeUseCase;
import be.kdg.prog6.landSideBoundedContext.port.in.weighTruckInCommand;
import be.kdg.prog6.landSideBoundedContext.port.in.weighTruckOutCommand;
import be.kdg.prog6.landSideBoundedContext.port.out.*;
import be.kdg.prog6.landSideBoundedContext.util.errorClasses.AppointmentDontExist;
import be.kdg.prog6.landSideBoundedContext.util.errorClasses.TruckIsNotAllowedToEnter;
import be.kdg.prog6.landSideBoundedContext.util.errorClasses.TruckIsNotOnTime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class weighBridgeUseCaseImp implements WeighBridgeUseCase {

    private static final Logger logger = LogManager.getLogger(weighBridgeUseCaseImp.class);

    private final WeighBridgeEventPublisher eventPublisher;
    private final CalendarLoadPort calendarLoadPort;
    private final WeighbridgeTicketLoadPort weighbridgeTicketLoadPort;
    private final WeighbridgeTicketSavePort weighbridgeTicketSavePort;
    private final WarehouseLoadPort warehouseLoadPort;

    public weighBridgeUseCaseImp(WeighBridgeEventPublisher eventPublisher, CalendarLoadPort calendarLoadPort, AppointmentSavePort appointmentSavePort, WeighbridgeTicketLoadPort weighbridgeTicketLoadPort, WeighbridgeTicketSavePort weighbridgeTicketSavePort, WarehouseLoadPort warehouseLoadPort) {
        this.eventPublisher = eventPublisher;
        this.calendarLoadPort = calendarLoadPort;
        this.weighbridgeTicketLoadPort = weighbridgeTicketLoadPort;
        this.weighbridgeTicketSavePort = weighbridgeTicketSavePort;
        this.warehouseLoadPort = warehouseLoadPort;
    }


    public WarehouseStatus assignAWarehouseTruck(SellerId sellerId, MaterialType materialType) {
        List<WareHouse> sellerWarehouseList = warehouseLoadPort.findAllBySellerId(sellerId.id());
        for (WareHouse warehouse : sellerWarehouseList) {
            if (warehouse.getMaterialType().equals(materialType)) {
                return warehouse.checkWarehouseCapacity();
            }
        }
        if (sellerWarehouseList.size() < 5) {
            return WarehouseStatus.CAN_CREATE;
        }
        throw new TruckIsNotAllowedToEnter(
                String.format("The truck is not allowed to enter. The seller has reached the maximum number of warehouses (%d) and cannot store more material of type %s.",
                        sellerWarehouseList.size(), materialType)
        );
    }


    @Override
    public void weighTruckIn(weighTruckInCommand command) {

        logger.info("check licensePlate of the truck ... ");
        WeighbridgeTicket weighbridgeTicket;
        WeighEvent weighEvent;
        try {
            DayCalendar dayCalendar = calendarLoadPort.loadAppointmentsByDate(command.weighInTime().toLocalDate());

            if (dayCalendar.allowTruckToEnter(command.licensePlate(), command.weighInTime())) {
                logger.info(" the Truck is on time the gate is open.....");
                weighbridgeTicket = new WeighbridgeTicket(new WeighBridgeTicketId(UUID.randomUUID()),
                        command.licensePlate(), command.sellerId(), command.startWeight(), 0,
                        command.materialType(), command.weighInTime());

                weighEvent = new WeighEvent(weighbridgeTicket.getWeighBridgeTicketId().id(),
                        weighbridgeTicket.getLicensePlate().licensePlate(), weighbridgeTicket.getSellerId().id(),
                        weighbridgeTicket.getStartWeight(), weighbridgeTicket.getMaterialType(),
                        weighbridgeTicket.getStartTime(),
                        assignAWarehouseTruck(weighbridgeTicket.getSellerId(), weighbridgeTicket.getMaterialType()));
                logger.info(" WeighbridgeTicket (WBT) has be created.....");
                logger.info(" weighInEvent has be created.....");

                weighbridgeTicketSavePort.save(weighbridgeTicket);
                eventPublisher.publishTruckWeightedIn(weighEvent);
            } else
                throw new TruckIsNotOnTime();
        } catch (NoSuchElementException ex) {
            throw new AppointmentDontExist("Appointment not found you may not Enter ");
        }

    }

    @Override
    public void weighTruckOut(weighTruckOutCommand command) {
        WeighbridgeTicket bridgeTicket = weighbridgeTicketLoadPort.loadById(command.sellerId().id());
        bridgeTicket.correctTicket(command.weighOutTime(), command.endWeight());
        weighbridgeTicketSavePort.save(bridgeTicket);

        WeighEvent weighEvent = new WeighEvent(bridgeTicket.getWeighBridgeTicketId().id(),
                bridgeTicket.getLicensePlate().licensePlate(), bridgeTicket.getSellerId().id(),
                bridgeTicket.getStartWeight(), bridgeTicket.getMaterialType(),
                bridgeTicket.getStartTime() , WarehouseStatus.DISREGARD);
        eventPublisher.publishTruckWeighedOut(weighEvent);

    }

    //TODO maybe make other useCase like a core for data projection for the event from the warehouse.
    //TODO make a method that listen to the event from the warehouse for the Mat true amount
    //TODO in here you would remove the appointment in the database
    // make a method in here
}

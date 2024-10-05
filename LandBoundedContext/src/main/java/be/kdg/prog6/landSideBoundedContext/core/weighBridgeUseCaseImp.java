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

import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class weighBridgeUseCaseImp implements WeighBridgeUseCase {

    private static final Logger logger = LogManager.getLogger(weighBridgeUseCaseImp.class);

    private final WeighBridgeEventPublisher eventPublisher;
    private final CalendarLoadPort calendarLoadPort;
    private AppointmentSavePort appointmentSavePort;
    private final WeighbridgeTicketLoadPort weighbridgeTicketLoadPort;
    private final WeighbridgeTicketSavePort weighbridgeTicketSavePort;
    private final WarehouseLoadPort warehouseLoadPort;
    private final WarehouseSavePort warehouseSavePort;


    public weighBridgeUseCaseImp(WeighBridgeEventPublisher eventPublisher, CalendarLoadPort calendarLoadPort, AppointmentSavePort appointmentSavePort, WeighbridgeTicketLoadPort weighbridgeTicketLoadPort, WeighbridgeTicketSavePort weighbridgeTicketSavePort, WarehouseLoadPort warehouseLoadPort, WarehouseSavePort warehouseSavePort) {
        this.eventPublisher = eventPublisher;
        this.calendarLoadPort = calendarLoadPort;
        this.weighbridgeTicketLoadPort = weighbridgeTicketLoadPort;
        this.weighbridgeTicketSavePort = weighbridgeTicketSavePort;
        this.warehouseLoadPort = warehouseLoadPort;
        this.warehouseSavePort = warehouseSavePort;
        this.appointmentSavePort = appointmentSavePort;
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
        throw new TruckIsNotAllowedToEnter(String.format("The truck is not allowed to enter. The seller has reached the maximum number of warehouses (%d) and cannot store more material of type %s.", sellerWarehouseList.size(), materialType));
    }


    @Override
    public void weighTruckIn(weighTruckInCommand command) {

        logger.info("check licensePlate of the truck ... ");
        WeighbridgeTicket weighbridgeTicket;
        WeighInEvent weighInEvent;
        try {
            DayCalendar dayCalendar = calendarLoadPort.loadAppointmentsByDate(command.weighInTime().toLocalDate());

            if (dayCalendar.allowTruckToEnter(command.licensePlate(), command.weighInTime())) {
                logger.info(" the Truck is on time the gate is open.....");
                weighbridgeTicket = new WeighbridgeTicket(new WeighBridgeTicketId(UUID.randomUUID()), command.licensePlate(), command.sellerId(), command.startWeight(), 0, command.materialType(), command.weighInTime());

                weighInEvent = new WeighInEvent(weighbridgeTicket.getWeighBridgeTicketId().id(), command.licensePlate().licensePlate(), command.sellerId().id(), command.startWeight(), command.materialType(), command.weighInTime(), assignAWarehouseTruck(command.sellerId(), command.materialType()));
                logger.info(" WeighbridgeTicket (WBT) has be created.....");
                logger.info(" weighInEvent has be created.....");

                weighbridgeTicketSavePort.save(weighbridgeTicket);
                eventPublisher.publishTruckWeightedIn(weighInEvent);
            } else throw new TruckIsNotOnTime();
        } catch (NoSuchElementException ex) {
            throw new AppointmentDontExist("Appointment not found you may not Enter ");
        }

    }

    @Override
    public void weighTruckOut(weighTruckOutCommand command) {
        WeighbridgeTicket bridgeTicket = weighbridgeTicketLoadPort.loadById(command.sellerId().id());
        bridgeTicket.correctTicket(command.weighOutTime(), command.endWeight());
        weighbridgeTicketSavePort.save(bridgeTicket);

        WeighOutEvent weighEvent = new WeighOutEvent(bridgeTicket.getWeighBridgeTicketId().id(), command.licensePlate().toString(), command.sellerId().id(), command.endWeight(), command.materialType(), command.weighOutTime(), WarehouseStatus.valueOf(command.warehouseStatus()));
        eventPublisher.publishTruckWeighedOut(weighEvent);
        appointmentDone(command);

    }

    private void appointmentDone(weighTruckOutCommand command) {
        DayCalendar calendar = calendarLoadPort.loadAppointmentsByDate(command.weighOutTime().toLocalDate());
        calendar.getAppointments().forEach(appointment -> {
            if (appointment.getLicensePlate() == command.licensePlate() && appointment.getSellerId() == command.sellerId()) {
                appointmentSavePort.deleteAppointment(appointment);
            }
        });

    }

    @Override
    public void updateWarehouse(UpdateWarehouseCommand updateWarehouseCommand) {
        WareHouse wareHouse = warehouseLoadPort.findById(updateWarehouseCommand.warehouseId().warehouseId());
        wareHouse.updateWarehouseMaterialAmount(updateWarehouseCommand.materialAmountInWarehouse());
        System.out.println(wareHouse.getAmountOfMaterial());
        warehouseSavePort.Save(wareHouse);


    }

}

package be.kdg.prog6.landSideBoundedContext.core;

import be.kdg.prog6.landSideBoundedContext.domain.Warehouse;
import be.kdg.prog6.landSideBoundedContext.port.in.ScheduleAppointmentCommand;
import be.kdg.prog6.landSideBoundedContext.port.in.ScheduleAppointmentUseCase;
import be.kdg.prog6.landSideBoundedContext.port.out.CalendarLoadPort;
import be.kdg.prog6.landSideBoundedContext.port.out.AppointmentSavePort;
import be.kdg.prog6.landSideBoundedContext.domain.Appointment;
import be.kdg.prog6.landSideBoundedContext.domain.DayCalendar;
import be.kdg.prog6.landSideBoundedContext.port.out.CalendarSavePort;
import be.kdg.prog6.landSideBoundedContext.port.out.WarehouseLoadPort;
import util.errorClasses.TimeSlotFullException;
import util.errorClasses.WarehouseIsFullException;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ScheduleAppointmentUseCaseImp implements ScheduleAppointmentUseCase {
    private static final Logger logger = LogManager.getLogger(ScheduleAppointmentUseCase.class);

    private final CalendarLoadPort calendarLoadPort;
    private final AppointmentSavePort appointmentSavePort;
    private final WarehouseLoadPort warehouseLoadPort;
    private final CalendarSavePort calendarSavePort;

    public ScheduleAppointmentUseCaseImp(AppointmentSavePort appointmentSavePort, CalendarLoadPort calendarLoadPort, WarehouseLoadPort warehouseLoadPort, CalendarSavePort calendarSavePort) {
        this.appointmentSavePort = appointmentSavePort;
        this.calendarLoadPort = calendarLoadPort;
        this.warehouseLoadPort = warehouseLoadPort;
        this.calendarSavePort = calendarSavePort;
    }

    @Override
    @Transactional
    public Appointment scheduleAppointment(ScheduleAppointmentCommand command) {
        try {
            Warehouse wareHouse = warehouseLoadPort.findBySellerIdAAndMaterialType(command.sellerId(), command.materialType());
            DayCalendar dayCalendar = calendarLoadPort.loadAppointmentsByDate(command.time().toLocalDate());
            if (!wareHouse.isFull()) {
                Appointment newAppointment = dayCalendar.scheduleAppointment(command);
                calendarSavePort.saveDayCalendar(dayCalendar);
                return newAppointment;
            } else {
                throw new WarehouseIsFullException("warehouse is full can not make Appointment");
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            throw new TimeSlotFullException(e.getMessage());
        }
    }
}

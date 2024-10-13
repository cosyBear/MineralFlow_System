package be.kdg.prog6.landSideBoundedContext.core;

import be.kdg.prog6.landSideBoundedContext.domain.WareHouse;
import be.kdg.prog6.landSideBoundedContext.domain.ScheduleAppointmentCommand;
import be.kdg.prog6.landSideBoundedContext.port.in.ScheduleAppointmentUseCase;
import be.kdg.prog6.landSideBoundedContext.port.out.CalendarLoadPort;
import be.kdg.prog6.landSideBoundedContext.port.out.AppointmentSavePort;
import be.kdg.prog6.landSideBoundedContext.domain.Appointment;
import be.kdg.prog6.landSideBoundedContext.domain.DayCalendar;
import be.kdg.prog6.landSideBoundedContext.port.out.WarehouseLoadPort;
import be.kdg.prog6.landSideBoundedContext.util.errorClasses.TimeSlotFullException;
import be.kdg.prog6.landSideBoundedContext.util.errorClasses.WarehouseISFull;
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

    public ScheduleAppointmentUseCaseImp(AppointmentSavePort appointmentSavePort, CalendarLoadPort calendarLoadPort, WarehouseLoadPort warehouseLoadPort) {
        this.appointmentSavePort = appointmentSavePort;
        this.calendarLoadPort = calendarLoadPort;
        this.warehouseLoadPort = warehouseLoadPort;
    }

    @Override
    @Transactional
    public Appointment scheduleAppointment(ScheduleAppointmentCommand command) {
        try {
            WareHouse wareHouse = warehouseLoadPort.findBySellerIdAAndMaterialType(command.sellerId().id(), command.materialType());
            DayCalendar dayCalendar = calendarLoadPort.loadAppointmentsByDate(command.time().toLocalDate());
            if (wareHouse.canStoreMaterial(command.payload())) {
                Appointment newAppointment = dayCalendar.scheduleAppointment(command);
                appointmentSavePort.saveAppointment(newAppointment);
                return newAppointment;

            } else
                throw new WarehouseISFull("warehouse is full can not make Appointment");
        } catch (Exception e) {
            logger.info(e.getMessage());
            throw new TimeSlotFullException(e.getMessage());
        }
    }
}

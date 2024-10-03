package be.kdg.prog6.landSideBoundedContext.core;
import be.kdg.prog6.landSideBoundedContext.port.in.ScheduleAppointmentCommand;
import be.kdg.prog6.landSideBoundedContext.port.in.ScheduleAppointmentUseCase;
import be.kdg.prog6.landSideBoundedContext.port.out.CalendarLoadPort;
import be.kdg.prog6.landSideBoundedContext.port.out.AppointmentSavePort;
import be.kdg.prog6.landSideBoundedContext.domain.Appointment;
import be.kdg.prog6.landSideBoundedContext.domain.DayCalendar;
import be.kdg.prog6.landSideBoundedContext.util.errorClasses.TimeSlotFullException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ScheduleAppointmentUseCaseImp implements ScheduleAppointmentUseCase {
    private static final Logger logger = LogManager.getLogger(ScheduleAppointmentUseCase.class);

    private final CalendarLoadPort calendarLoadPort;
    private final AppointmentSavePort appointmentSavePort;
    public ScheduleAppointmentUseCaseImp(AppointmentSavePort appointmentSavePort, CalendarLoadPort calendarLoadPort) {
        this.appointmentSavePort = appointmentSavePort;
        this.calendarLoadPort = calendarLoadPort;
    }

    @Override
    public Appointment scheduleAppointment(ScheduleAppointmentCommand command) {
        try {
            DayCalendar dayCalendar = calendarLoadPort.loadAppointmentsByDate(command.time().toLocalDate());
            Appointment newAppointment = dayCalendar.scheduleAppointment(command);
             appointmentSavePort.saveAppointment(newAppointment);
            return newAppointment;
        } catch (Exception e)  {
            logger.info(e.getMessage());
            throw new TimeSlotFullException(e.getMessage());
        }
    }
}

package be.kdg.prog6.LandSideBoundedContext.Core;
import be.kdg.prog6.LandSideBoundedContext.Port.in.ScheduleAppointmentCommand;
import be.kdg.prog6.LandSideBoundedContext.Port.in.ScheduleAppointmentPort;
import be.kdg.prog6.LandSideBoundedContext.Port.out.CalendarLoadPort;
import be.kdg.prog6.LandSideBoundedContext.domain.Appointment;
import be.kdg.prog6.LandSideBoundedContext.domain.Calendar;
import be.kdg.prog6.LandSideBoundedContext.util.TimeSlotFullException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ScheduleAppointmentUseCase implements ScheduleAppointmentPort {
    private static final Logger logger = LogManager.getLogger(ScheduleAppointmentPort.class);
    private final CalendarLoadPort calendarLoadPort; // Inject the Out Port (Output Port)

    public ScheduleAppointmentUseCase(CalendarLoadPort calendarLoadPort) {
        this.calendarLoadPort = calendarLoadPort;
    }

    @Override
    public ScheduleAppointmentCommand scheduleAppointment(ScheduleAppointmentCommand command) {
        try {
            // Step 1: Load the current calendar
            Calendar calendar = calendarLoadPort.loadAppointmentsIntoCalendar();

            // Step 2: Schedule the appointment
            Appointment newAppointment = calendar.scheduleAppointment(command);

            // Step 3: Return a new ScheduleAppointmentCommand, or just return the original command
            return new ScheduleAppointmentCommand(
                    command.sellerId(),
                    newAppointment.getTruck().getLicenseNumber(),
                    newAppointment.getTruck().getPayload(),
                    newAppointment.getMaterialType().toString(),
                    newAppointment.getDate(),
                    newAppointment.getTimeSlot().earliestArravieTime(),
                    newAppointment.getTimeSlot().latestArravieTime()
            );
        } catch (TimeSlotFullException e)  { // catch the exception from the calendar.scheduleAppointment that you throw if there is an error
            logger.info(e.getMessage());
            throw e; // Rethrow the exception if the time slot is full
        }
    }
}

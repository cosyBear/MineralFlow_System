package be.kdg.prog6.LandSideBoundedContext.Core;
import be.kdg.prog6.LandSideBoundedContext.Port.in.ScheduleAppointmentCommand;
import be.kdg.prog6.LandSideBoundedContext.Port.in.ScheduleAppointmentPort;
import be.kdg.prog6.LandSideBoundedContext.Port.out.CalendarLoadPort;
import be.kdg.prog6.LandSideBoundedContext.Port.out.CalendarSavePort;
import be.kdg.prog6.LandSideBoundedContext.adapters.out.entity.AppointmentEntity;
import be.kdg.prog6.LandSideBoundedContext.domain.Appointment;
import be.kdg.prog6.LandSideBoundedContext.domain.Calendar;
import be.kdg.prog6.LandSideBoundedContext.util.TimeSlotFullException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ScheduleAppointmentUseCase implements ScheduleAppointmentPort {
    private static final Logger logger = LogManager.getLogger(ScheduleAppointmentPort.class);
    private final CalendarLoadPort calendarLoadPort; // Inject the Out Port (Output Port)
    CalendarSavePort calendarSavePort;
    private final ModelMapper modelMapper;

    public ScheduleAppointmentUseCase(CalendarLoadPort calendarLoadPort, CalendarSavePort calendarSavePort , ModelMapper modelMapper ) {
        this.calendarLoadPort = calendarLoadPort;
        this.calendarSavePort = calendarSavePort;
        this.modelMapper = modelMapper;
    }

    @Override
    public ScheduleAppointmentCommand scheduleAppointment(ScheduleAppointmentCommand command) {
        try {
            //Load the current calendar
            // note in the future  maybe load the calendar for that day or that time slot
            Calendar calendar = calendarLoadPort.loadAppointmentsByDate(command.date());

            // Step 2: Schedule the appointment
            Appointment newAppointment = calendar.scheduleAppointment(command);

            calendarSavePort.SaveAppointment(modelMapper.map(newAppointment, AppointmentEntity.class));
            return new ScheduleAppointmentCommand(// for now im going to do it like this in the future maybe just send him a message.
                    command.sellerId(),
                    newAppointment.getTruck().getLicenseNumber(),
                    newAppointment.getTruck().getPayload(),
                    String.valueOf(newAppointment.getMaterialType()), //safer convertion to string
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

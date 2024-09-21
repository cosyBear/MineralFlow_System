package be.kdg.prog6.LandSideBoundedContext.core;
import be.kdg.prog6.LandSideBoundedContext.port.in.ScheduleAppointmentCommand;
import be.kdg.prog6.LandSideBoundedContext.port.in.ScheduleAppointmentPort;
import be.kdg.prog6.LandSideBoundedContext.port.out.CalendarLoadPort;
import be.kdg.prog6.LandSideBoundedContext.port.out.AppointmentSavePort;
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
    private final CalendarLoadPort calendarLoadPort;
    AppointmentSavePort appointmentSavePort;
    private final ModelMapper modelMapper;

    public ScheduleAppointmentUseCase(CalendarLoadPort calendarLoadPort, AppointmentSavePort appointmentSavePort, ModelMapper modelMapper ) {
        this.calendarLoadPort = calendarLoadPort;
        this.appointmentSavePort = appointmentSavePort;
        this.modelMapper = modelMapper;
    }

    @Override
    public ScheduleAppointmentCommand scheduleAppointment(ScheduleAppointmentCommand command) {
        try {
            Calendar calendar = calendarLoadPort.loadAppointmentsByDate(command.date());

            Appointment newAppointment = calendar.scheduleAppointment(command);

            appointmentSavePort.saveAppointment(modelMapper.map(newAppointment, AppointmentEntity.class));


            return new ScheduleAppointmentCommand(
                    newAppointment.getTruck().getLicenseNumber(),
                    newAppointment.getTruck().getPayload(),
                    String.valueOf(newAppointment.getMaterialType()),
                    newAppointment.getDate(),
                    newAppointment.getTimeSlot().earliestArravieTime(),
                    newAppointment.getTimeSlot().latestArravieTime(),
                    newAppointment.getCompanyName()
            );
        } catch (TimeSlotFullException e)  {
            logger.info(e.getMessage());
            throw e;
        }
    }
}

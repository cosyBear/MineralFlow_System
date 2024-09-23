package be.kdg.prog6.LandSideBoundedContext.core;
import be.kdg.prog6.LandSideBoundedContext.port.in.ScheduleAppointmentCommand;
import be.kdg.prog6.LandSideBoundedContext.port.in.ScheduleAppointmentUseCase;
import be.kdg.prog6.LandSideBoundedContext.port.out.CalendarLoadPort;
import be.kdg.prog6.LandSideBoundedContext.port.out.AppointmentSavePort;
import be.kdg.prog6.LandSideBoundedContext.domain.Appointment;
import be.kdg.prog6.LandSideBoundedContext.domain.Calendar;
import be.kdg.prog6.LandSideBoundedContext.util.TimeSlotFullException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ScheduleAppointmentUseCaseImp implements ScheduleAppointmentUseCase {
    private static final Logger logger = LogManager.getLogger(ScheduleAppointmentUseCase.class);

    private  CalendarLoadPort calendarLoadPort;
    private AppointmentSavePort appointmentSavePort;
    private final ModelMapper modelMapper;
    public ScheduleAppointmentUseCaseImp(ModelMapper modelMapper, AppointmentSavePort appointmentSavePort, CalendarLoadPort calendarLoadPort) {
        this.modelMapper = modelMapper;
        this.appointmentSavePort = appointmentSavePort;
        this.calendarLoadPort = calendarLoadPort;
    }

    @Override
    public ScheduleAppointmentCommand scheduleAppointment(ScheduleAppointmentCommand command) {
        try {
            Calendar calendar = calendarLoadPort.loadAppointmentsByDate(command.time().toLocalDate());

            Appointment newAppointment = calendar.scheduleAppointment(command);
            /// load the wearhous cap from the databvase in the lANDSIDE and the landSIde will have a wearhouse class \
            // so what the teacher said is you load the warehouse  data which  is just events you get from the other bounded context
           appointmentSavePort.saveAppointment(newAppointment);


            return new ScheduleAppointmentCommand(
                    newAppointment.getLicensePlate(),
                    newAppointment.getMaterialType(),
                    newAppointment.getTime(),
                    newAppointment.getSellerId()
            );
        } catch (TimeSlotFullException e)  {
            logger.info(e.getMessage());
            throw e;
        }
    }
}

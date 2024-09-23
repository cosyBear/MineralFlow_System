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
public class ScheduleAppointmentUseCaseImp implements ScheduleAppointmentPort {
    private static final Logger logger = LogManager.getLogger(ScheduleAppointmentPort.class);

    private final CalendarLoadPort calendarLoadPort;
    AppointmentSavePort appointmentSavePort;

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
            // make
            /// load the wearhous cap from the databvase in the lANDSIDE and the landSIde will have a wearhouse class \
//            appointmentSavePort.saveAppointment(modelMapper.map(newAppointment, AppointmentEntity.class));

           appointmentSavePort.saveAppointment(newAppointment);


            return new ScheduleAppointmentCommand(
                    newAppointment.getLicensePlate(),
                    newAppointment.getMaterialType(),
                    newAppointment.getDate(),
                    newAppointment.getSellerId()
            );
        } catch (TimeSlotFullException e)  {
            logger.info(e.getMessage());
            throw e;
        }
    }
}

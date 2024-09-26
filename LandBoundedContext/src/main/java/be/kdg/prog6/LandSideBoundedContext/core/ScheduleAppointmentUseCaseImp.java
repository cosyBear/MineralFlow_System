package be.kdg.prog6.LandSideBoundedContext.core;
import be.kdg.prog6.LandSideBoundedContext.port.in.ScheduleAppointmentCommand;
import be.kdg.prog6.LandSideBoundedContext.port.in.ScheduleAppointmentUseCase;
import be.kdg.prog6.LandSideBoundedContext.port.out.CalendarLoadPort;
import be.kdg.prog6.LandSideBoundedContext.port.out.AppointmentSavePort;
import be.kdg.prog6.LandSideBoundedContext.domain.Appointment;
import be.kdg.prog6.LandSideBoundedContext.domain.Calendar;
import be.kdg.prog6.LandSideBoundedContext.util.errorClasses.TimeSlotFullException;
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
           appointmentSavePort.saveAppointment(newAppointment);


            return new ScheduleAppointmentCommand(
                    newAppointment.getLicensePlate(),
                    newAppointment.getMaterialType(),
                    newAppointment.getTime(),
                    newAppointment.getSellerId()
            );
        } catch (TimeSlotFullException timeSlotFullException)  {
            logger.info(timeSlotFullException.getMessage());
            throw timeSlotFullException;
        }
    }
}
// TODO As a truck driver, I want to dock to the correct conveyor belt and receive my copy of the PDT and new weighing bridge number
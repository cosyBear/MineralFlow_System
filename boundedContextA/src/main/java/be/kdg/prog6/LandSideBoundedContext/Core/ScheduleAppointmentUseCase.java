package be.kdg.prog6.LandSideBoundedContext.Core;

import be.kdg.prog6.LandSideBoundedContext.Dto.ScheduleAppointmentCommand;
import be.kdg.prog6.LandSideBoundedContext.Port.in.ScheduleAppointmentPort;
import be.kdg.prog6.LandSideBoundedContext.util.TimeSlotFullException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.ILoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ScheduleAppointmentUseCase implements ScheduleAppointmentPort {
    private final Calendar calendar = new Calendar();
    private static final Logger logger = LogManager.getLogger(ScheduleAppointmentPort.class);

    @Override
    public void scheduleAppointment(ScheduleAppointmentCommand command) {
        try {
            calendar.scheduleAppointment(command);
        } catch (TimeSlotFullException e) {
            logger.info(e.getMessage());
            throw e;
        }
    }
}


package be.kdg.prog6.LandSideBoundedContext.adapters.out.persistence;

import be.kdg.prog6.LandSideBoundedContext.port.in.ScheduleAppointmentPort;
import be.kdg.prog6.LandSideBoundedContext.adapters.out.entity.AppointmentEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AppointmentSavePort implements be.kdg.prog6.LandSideBoundedContext.port.out.AppointmentSavePort {

    private final AppointmentRepository appointmentRepository;
    private final ModelMapper modelMapper;
    private static final Logger logger = LogManager.getLogger(ScheduleAppointmentPort.class);

    public AppointmentSavePort(AppointmentRepository appointmentRepository, ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveAppointment(AppointmentEntity AppointmentEntity) {
        try {
            appointmentRepository.save(AppointmentEntity);
        }
        catch (Exception e) {
            logger.debug(e.getMessage() + " ERROR from saveAppointment in the CalendarSavePortCommand class ");

        }
    }

}

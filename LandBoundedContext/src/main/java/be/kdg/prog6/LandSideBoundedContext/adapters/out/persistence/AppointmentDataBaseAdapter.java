package be.kdg.prog6.LandSideBoundedContext.adapters.out.persistence;

import be.kdg.prog6.LandSideBoundedContext.adapters.out.entity.AppointmentEntity;
import be.kdg.prog6.LandSideBoundedContext.domain.Appointment;
import be.kdg.prog6.LandSideBoundedContext.port.in.ScheduleAppointmentUseCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AppointmentDataBaseAdapter implements be.kdg.prog6.LandSideBoundedContext.port.out.AppointmentSavePort {

    private final AppointmentRepository appointmentRepository;
    private final ModelMapper modelMapper;
    private static final Logger logger = LogManager.getLogger(ScheduleAppointmentUseCase.class);


    public AppointmentDataBaseAdapter(AppointmentRepository appointmentRepository, ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveAppointment(Appointment Appointment)throws Exception  {
        try {
            appointmentRepository.save(modelMapper.map(Appointment, AppointmentEntity.class));
        }
        catch (Exception e) {
            throw e;
        }
    }

}

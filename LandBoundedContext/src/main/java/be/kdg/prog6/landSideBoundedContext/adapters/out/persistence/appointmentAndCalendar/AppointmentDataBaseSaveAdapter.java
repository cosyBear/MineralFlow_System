package be.kdg.prog6.landSideBoundedContext.adapters.out.persistence.appointmentAndCalendar;

import be.kdg.prog6.landSideBoundedContext.adapters.out.entity.AppointmentEntity;
import be.kdg.prog6.landSideBoundedContext.adapters.out.entity.MaterialTypeEntity;
import be.kdg.prog6.landSideBoundedContext.domain.Appointment;
import be.kdg.prog6.landSideBoundedContext.port.in.ScheduleAppointmentUseCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import be.kdg.prog6.landSideBoundedContext.port.out.AppointmentSavePort;
@Service
public class AppointmentDataBaseSaveAdapter implements AppointmentSavePort  {

    private final AppointmentRepository appointmentRepository;
    private final ModelMapper modelMapper;
    private static final Logger logger = LogManager.getLogger(ScheduleAppointmentUseCase.class);


    public AppointmentDataBaseSaveAdapter(AppointmentRepository appointmentRepository, ModelMapper modelMapper) {
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

    @Override
    public void deleteAppointment(Appointment Appointment)  {

        AppointmentEntity appointmentEntity = appointmentRepository.findBySellerIdAndMaterialType(Appointment.getSellerId().id() , MaterialTypeEntity.valueOf(Appointment.getMaterialType().toString()));

        appointmentRepository.delete(appointmentEntity);
    }

}

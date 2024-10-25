package be.kdg.prog6.landSideBoundedContext.adapters.out.persistence.appointmentAndCalendar;

import be.kdg.prog6.landSideBoundedContext.adapters.out.entity.AppointmentEntity;
import be.kdg.prog6.landSideBoundedContext.adapters.out.entity.MaterialTypeEntity;
import be.kdg.prog6.landSideBoundedContext.domain.Appointment;
import be.kdg.prog6.landSideBoundedContext.port.in.ScheduleAppointmentUseCase;
import util.errorClasses.AppointmentDontExistException;
import util.errorClasses.CouldNotSaveAppointmentException;
import util.errorClasses.DatabaseOperationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import be.kdg.prog6.landSideBoundedContext.port.out.AppointmentSavePort;
@Service
public class AppointmentDataBaseAdapter implements AppointmentSavePort  {

    private final AppointmentRepository appointmentRepository;
    private final ModelMapper modelMapper;
    private static final Logger logger = LogManager.getLogger(ScheduleAppointmentUseCase.class);


    public AppointmentDataBaseAdapter(AppointmentRepository appointmentRepository, @Qualifier("landModelMapper")ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveAppointment(Appointment appointment) {
        try {
            appointmentRepository.save(modelMapper.map(appointment, AppointmentEntity.class));
        } catch (ConstraintViolationException | DataAccessException e) {
            throw new CouldNotSaveAppointmentException("Database error: Could not save the appointment. The appointment may already exist or there is a data integrity issue.");
        } catch (Exception e) {
            throw new CouldNotSaveAppointmentException("Unexpected error: Could not save the appointment.");
        }
    }

    @Override
    public void deleteAppointment(Appointment appointment) {
        AppointmentEntity appointmentEntity = appointmentRepository.findBySellerIdAndMaterialType(
                appointment.getSellerId().id(),
                MaterialTypeEntity.valueOf(appointment.getMaterialType().toString())
        );
        if (appointmentEntity == null) {
            throw new AppointmentDontExistException("Appointment not found for seller ID: "
                    + appointment.getSellerId().id() + " and material type: " + appointment.getMaterialType());
        }
        try {
            appointmentRepository.delete(appointmentEntity);
        } catch (DataAccessException e) {
            throw new DatabaseOperationException("Failed to delete appointment due to database error " +  e);
        }
    }


}

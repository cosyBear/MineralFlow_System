package be.kdg.prog6.landSideBoundedContext.adapters.out.persistence.appointmentAndCalendar;

import be.kdg.prog6.landSideBoundedContext.port.in.ScheduleAppointmentUseCase;
import be.kdg.prog6.landSideBoundedContext.adapters.out.entity.AppointmentEntity;
import be.kdg.prog6.landSideBoundedContext.domain.Appointment;
import be.kdg.prog6.landSideBoundedContext.domain.DayCalendar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class CalendarDataBaseLoadAdapter implements be.kdg.prog6.landSideBoundedContext.port.out.CalendarLoadPort {

    private final AppointmentRepository appointmentRepository;
    private final ModelMapper modelMapper;
    private static final Logger logger = LogManager.getLogger(ScheduleAppointmentUseCase.class);

    public CalendarDataBaseLoadAdapter(AppointmentRepository appointmentRepository, ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DayCalendar loadAppointmentsByDate(LocalDate date) {
        // add some error handling if the apAppointment dont exist throw it to the controller to catch it
        List<AppointmentEntity> entities = appointmentRepository.findAppointmentsByDate(date);
        return createCalendarFromEntities(entities);
    }

    private DayCalendar createCalendarFromEntities(List<AppointmentEntity> entities) {
        DayCalendar dayCalendar = new DayCalendar();
        List<Appointment> appointments = new ArrayList<>();
        for (AppointmentEntity entity : entities) {
            appointments.add(modelMapper.map(entity, Appointment.class));
        }
        dayCalendar.setAppointments(appointments);
        return dayCalendar;
    }
}





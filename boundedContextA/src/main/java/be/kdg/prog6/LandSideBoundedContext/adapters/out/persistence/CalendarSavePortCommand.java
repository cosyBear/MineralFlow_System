package be.kdg.prog6.LandSideBoundedContext.adapters.out.persistence;

import be.kdg.prog6.LandSideBoundedContext.Port.out.CalendarSavePort;
import be.kdg.prog6.LandSideBoundedContext.adapters.out.entity.AppointmentEntity;
import be.kdg.prog6.LandSideBoundedContext.domain.Appointment;
import be.kdg.prog6.LandSideBoundedContext.domain.Calendar;
import be.kdg.prog6.LandSideBoundedContext.domain.TimeSlot;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
@Service
public class CalendarSavePortCommand implements CalendarSavePort {

    private final AppointmentRepository appointmentRepository;
    private final ModelMapper modelMapper;

    public CalendarSavePortCommand(AppointmentRepository appointmentRepository, ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public void SaveCalendar(Calendar calendar) {
        Map<LocalDate, Map<TimeSlot, List<Appointment>>> appointmentsMap = calendar.getAppointments();

        for (Map.Entry<LocalDate, Map<TimeSlot, List<Appointment>>> dateEntry : appointmentsMap.entrySet()) {
            LocalDate date = dateEntry.getKey();
            Map<TimeSlot, List<Appointment>> timeSlotMap = dateEntry.getValue();

            for (Map.Entry<TimeSlot, List<Appointment>> timeSlotEntry : timeSlotMap.entrySet()) {
                TimeSlot timeSlot = timeSlotEntry.getKey();
                List<Appointment> appointments = timeSlotEntry.getValue();

                for (Appointment appointment : appointments) {
                    AppointmentEntity appointmentEntity = modelMapper.map(appointment, AppointmentEntity.class);
                    appointmentRepository.save(appointmentEntity);
                }
            }
        }
    }

}

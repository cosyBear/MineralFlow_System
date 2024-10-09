package be.kdg.prog6.landSideBoundedContext.core;

import be.kdg.prog6.landSideBoundedContext.domain.Appointment;
import be.kdg.prog6.landSideBoundedContext.domain.AppointmentQuery;
import be.kdg.prog6.landSideBoundedContext.port.out.CalendarLoadPort;
import be.kdg.prog6.landSideBoundedContext.port.out.WarehouseManagerQueryUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class WarehouseManagerQueryUseCaseImp implements WarehouseManagerQueryUseCase {


    private final CalendarLoadPort calendarLoadPort;
    private final ModelMapper modelMapper;

    public WarehouseManagerQueryUseCaseImp(CalendarLoadPort calendarLoadPort, ModelMapper modelMapper) {
        this.calendarLoadPort = calendarLoadPort;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<AppointmentQuery> fetchTrucksOnSite(LocalDate time) {

            List<Appointment> appointmentList = calendarLoadPort.fetchTrucksOnSite(time).getAppointments();
            List<AppointmentQuery> appointmentQueryList = new ArrayList<>();
            for (Appointment appointment : appointmentList) {
                appointmentQueryList.add(modelMapper.map(appointment, AppointmentQuery.class));
            }
        return appointmentQueryList;
    }


}

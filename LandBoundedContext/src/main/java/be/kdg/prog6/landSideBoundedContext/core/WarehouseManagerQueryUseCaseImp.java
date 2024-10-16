package be.kdg.prog6.landSideBoundedContext.core;

import be.kdg.prog6.landSideBoundedContext.domain.*;
import be.kdg.prog6.landSideBoundedContext.domain.AppointmentQuery;
import be.kdg.prog6.landSideBoundedContext.port.out.CalendarLoadPort;
import be.kdg.prog6.landSideBoundedContext.port.out.WarehouseLoadPort;
import be.kdg.prog6.landSideBoundedContext.port.out.WarehouseManagerQueryUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class WarehouseManagerQueryUseCaseImp implements WarehouseManagerQueryUseCase {


    private final CalendarLoadPort calendarLoadPort;
    private final ModelMapper modelMapper;
    private final WarehouseLoadPort warehouseLoadPort;

    public WarehouseManagerQueryUseCaseImp(CalendarLoadPort calendarLoadPort, ModelMapper modelMapper , WarehouseLoadPort warehouseLoadPort) {
        this.calendarLoadPort = calendarLoadPort;
        this.modelMapper = modelMapper;
        this.warehouseLoadPort = warehouseLoadPort;
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

    @Override
    public List<AppointmentQuery> fetchTrucksOnTime(LocalDate time) {
        List<Appointment> appointmentList = calendarLoadPort.fetchTrucksOnTime(time).getAppointments();
        List<AppointmentQuery> appointmentQueryList = new ArrayList<>();
        for (Appointment appointment : appointmentList) {
            appointmentQueryList.add(modelMapper.map(appointment, AppointmentQuery.class));
        }
        return appointmentQueryList;
    }


    @Override
    @Transactional
    public List<WarehouseOverviewQuery> WarehouseOverview() {
        return mapToOverviewQuery(warehouseLoadPort.warehouseOverview());
    }



    public List<WarehouseOverviewQuery> mapToOverviewQuery(List<Warehouse> warehouseList) {
            List<WarehouseOverviewQuery> warehouseOverviewQueryList = new ArrayList<>();
        for(Warehouse wareHouse : warehouseList){
            WarehouseOverviewQuery warehouseOverviewQuery = new WarehouseOverviewQuery(wareHouse.getWarehouseId().warehouseId()
            , wareHouse.getSellerId().id() , wareHouse.getAmountOfMaterial() , wareHouse.getMaterialType()
            ,wareHouse.isFull() , wareHouse.isAtOverflow());
            warehouseOverviewQueryList.add(warehouseOverviewQuery);
        }
        return warehouseOverviewQueryList;

    }

}

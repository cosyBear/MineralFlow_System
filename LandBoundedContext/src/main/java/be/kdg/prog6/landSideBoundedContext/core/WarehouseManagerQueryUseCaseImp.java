package be.kdg.prog6.landSideBoundedContext.core;

import be.kdg.prog6.landSideBoundedContext.domain.*;
import be.kdg.prog6.landSideBoundedContext.port.in.TruckOnTimeQuery;
import be.kdg.prog6.landSideBoundedContext.port.in.WarehouseOverviewQuery;
import be.kdg.prog6.landSideBoundedContext.port.out.CalendarLoadPort;
import be.kdg.prog6.landSideBoundedContext.port.out.WarehouseLoadPort;
import be.kdg.prog6.landSideBoundedContext.port.out.WarehouseManagerQueryUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class WarehouseManagerQueryUseCaseImp implements WarehouseManagerQueryUseCase {


    private final CalendarLoadPort calendarLoadPort;
    private final ModelMapper modelMapper;
    private final WarehouseLoadPort warehouseLoadPort;

    public WarehouseManagerQueryUseCaseImp(CalendarLoadPort calendarLoadPort, @Qualifier("landModelMapper") ModelMapper modelMapper, WarehouseLoadPort warehouseLoadPort) {
        this.calendarLoadPort = calendarLoadPort;
        this.modelMapper = modelMapper;
        this.warehouseLoadPort = warehouseLoadPort;
    }


    @Override
    @Transactional
    public Integer fetchTrucksOnSite(LocalDate time) {
        return (int)calendarLoadPort.fetchTrucksOnSite(time);
    }

    @Override
    @Transactional
    public List<TruckOnTimeQuery> fetchTrucksOnTime(LocalDate time) {
        List<Appointment> appointmentList = calendarLoadPort.fetchTrucksOnTime(time).getAppointments();
        List<TruckOnTimeQuery> truckOnTimeQueryList = new ArrayList<>();
        for (Appointment appointment : appointmentList) {
            System.out.println("Current Time: " + LocalDateTime.now());
            System.out.println("Appointment Time: " + appointment.getTime());
            truckOnTimeQueryList.add(new TruckOnTimeQuery(appointment.getLicensePlate().licensePlate(), appointment.getSellerId().id(),appointment.getMaterialType().toString() ,  appointment.getTime(), appointment.isTruckOnTime(LocalDateTime.now())));
        }
        return truckOnTimeQueryList;
    }


    @Override
    @Transactional
    public List<WarehouseOverviewQuery> WarehouseOverview() {
        return mapToOverviewQuery(warehouseLoadPort.warehouseOverview());
    }


    public List<WarehouseOverviewQuery> mapToOverviewQuery(List<Warehouse> warehouseList) {
        List<WarehouseOverviewQuery> warehouseOverviewQueryList = new ArrayList<>();
        for (Warehouse wareHouse : warehouseList) {
            WarehouseOverviewQuery warehouseOverviewQuery = new WarehouseOverviewQuery(wareHouse.getWarehouseId().warehouseId()
                    , wareHouse.getSellerId().id(), wareHouse.getAmountOfMaterial(), wareHouse.getMaterialType()
                    , wareHouse.isFull(), wareHouse.isAtOverflow());
            warehouseOverviewQueryList.add(warehouseOverviewQuery);
        }
        return warehouseOverviewQueryList;

    }

}

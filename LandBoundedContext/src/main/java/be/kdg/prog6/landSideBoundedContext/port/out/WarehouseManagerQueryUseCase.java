package be.kdg.prog6.landSideBoundedContext.port.out;

import be.kdg.prog6.landSideBoundedContext.domain.AppointmentQuery;
import be.kdg.prog6.landSideBoundedContext.domain.WarehouseOverviewQuery;

import java.time.LocalDate;
import java.util.List;

public interface WarehouseManagerQueryUseCase {


    List<AppointmentQuery> fetchTrucksOnSite(LocalDate time);

    List<WarehouseOverviewQuery> WarehouseOverview();

    List<AppointmentQuery> fetchTrucksOnTime(LocalDate time);

}

package be.kdg.prog6.landSideBoundedContext.port.out;

import be.kdg.prog6.landSideBoundedContext.domain.TruckOnSiteQuery;
import be.kdg.prog6.landSideBoundedContext.domain.TruckOnTimeQuery;
import be.kdg.prog6.landSideBoundedContext.domain.WarehouseOverviewQuery;

import java.time.LocalDate;
import java.util.List;

public interface WarehouseManagerQueryUseCase {


    Integer fetchTrucksOnSite(LocalDate time);

    List<WarehouseOverviewQuery> WarehouseOverview();

    List<TruckOnTimeQuery> fetchTrucksOnTime(LocalDate time);

}

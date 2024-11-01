package be.kdg.prog6.landSideBoundedContext.port.out;

import be.kdg.prog6.landSideBoundedContext.port.in.TruckOnTimeQuery;

import java.time.LocalDate;
import java.util.List;

public interface WarehouseManagerQueryUseCase {


    Integer fetchTrucksOnSite();


    List<TruckOnTimeQuery> fetchTrucksOnTime(LocalDate time);

}

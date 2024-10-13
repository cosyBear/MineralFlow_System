package be.kdg.prog6.landSideBoundedContext.adapters.in;


import be.kdg.prog6.landSideBoundedContext.domain.AppointmentQuery;
import be.kdg.prog6.landSideBoundedContext.domain.WarehouseOverviewQuery;
import be.kdg.prog6.landSideBoundedContext.port.out.WarehouseManagerQueryUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/warehousemanager")
public class WarehouseManagerController {

    private final WarehouseManagerQueryUseCase warehouseManagerQueryUseCase;

    public WarehouseManagerController(WarehouseManagerQueryUseCase warehouseManagerQueryUseCase) {
        this.warehouseManagerQueryUseCase = warehouseManagerQueryUseCase;
    }


    @GetMapping("/trucks/onSite")
    public ResponseEntity<List<AppointmentQuery>> trucksOnSite(@RequestParam("date") LocalDate date) {
        return ResponseEntity.ok().body(warehouseManagerQueryUseCase.fetchTrucksOnSite(date));
    }


    @GetMapping("/trucks/onTime")
    public ResponseEntity<List<AppointmentQuery>> trucksOnTime(@RequestParam("date") LocalDate date) {
        return ResponseEntity.ok().body(warehouseManagerQueryUseCase.fetchTrucksOnTime(date));
    }



    @GetMapping("/warehouses")
    // tis will give you overview of all your whereHouses
    public ResponseEntity<List<WarehouseOverviewQuery>> warehouseOverview(){
        return ResponseEntity.ok().body(warehouseManagerQueryUseCase.WarehouseOverview());
    }




}

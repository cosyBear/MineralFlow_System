package be.kdg.prog6.landSideBoundedContext.adapters.in;


import be.kdg.prog6.landSideBoundedContext.domain.TruckOnSiteQuery;
import be.kdg.prog6.landSideBoundedContext.domain.TruckOnTimeQuery;
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
    public ResponseEntity<List<TruckOnSiteQuery>> trucksOnSite(@RequestParam("date") LocalDate date) {
        return ResponseEntity.ok().body(warehouseManagerQueryUseCase.fetchTrucksOnSite(date));
    }

    @CrossOrigin(origins = "http://localhost:5173")  // Allow frontend requests from localhost:5173
    @GetMapping("/trucks/onTime")
    public ResponseEntity<List<TruckOnTimeQuery>> trucksOnTime(@RequestParam("date") LocalDate date) {
        return ResponseEntity.ok().body(warehouseManagerQueryUseCase.fetchTrucksOnTime(date));
    }



    @GetMapping("/warehouses")
    // tis will give you overview of all your whereHouses
    public ResponseEntity<List<WarehouseOverviewQuery>> warehouseOverview(){
        return ResponseEntity.ok().body(warehouseManagerQueryUseCase.WarehouseOverview());
    }




}

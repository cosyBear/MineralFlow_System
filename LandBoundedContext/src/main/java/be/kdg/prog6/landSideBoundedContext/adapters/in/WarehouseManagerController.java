package be.kdg.prog6.landSideBoundedContext.adapters.in;


import be.kdg.prog6.landSideBoundedContext.port.in.TruckOnTimeQuery;
import be.kdg.prog6.landSideBoundedContext.port.out.WarehouseManagerQueryUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('Manager')")
    public ResponseEntity<Integer> trucksOnSite() {
        return ResponseEntity.ok().body(warehouseManagerQueryUseCase.fetchTrucksOnSite());
    }

    @GetMapping("/trucks/onTime")
    @PreAuthorize("hasAuthority('Manager')")
    public ResponseEntity<List<TruckOnTimeQuery>> trucksOnTime(@RequestParam("date") LocalDate date) {

        return ResponseEntity.ok().body(warehouseManagerQueryUseCase.fetchTrucksOnTime(date));


    }


}

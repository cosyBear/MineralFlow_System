package be.kdg.prog6.landSideBoundedContext.adapters.in;


import be.kdg.prog6.landSideBoundedContext.domain.AppointmentQuery;
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
        return ResponseEntity.ok(warehouseManagerQueryUseCase.fetchTrucksOnSite(date));
    }




}

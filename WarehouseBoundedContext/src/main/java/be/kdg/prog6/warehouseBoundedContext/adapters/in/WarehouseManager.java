package be.kdg.prog6.warehouseBoundedContext.adapters.in;


import be.kdg.prog6.warehouseBoundedContext.port.in.WarehouseManagerPort;
import be.kdg.prog6.warehouseBoundedContext.port.in.WarehouseQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("warehouseManager")
public class WarehouseManager {


    private final WarehouseManagerPort warehouseManagerPort;

    public WarehouseManager(WarehouseManagerPort warehouseManagerPort) {
        this.warehouseManagerPort = warehouseManagerPort;
    }


    @GetMapping
    @PreAuthorize("hasAuthority('Manager')")
    public ResponseEntity<List<WarehouseQuery>> getAllWarehouses() {
        List<WarehouseQuery> warehouseQueryList = warehouseManagerPort.warehouseQueryList();
        return ResponseEntity.status(HttpStatus.OK).body(warehouseQueryList);

    }


}

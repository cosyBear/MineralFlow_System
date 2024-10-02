package be.kdg.prog6.boundedcontextB.core;

import be.kdg.prog6.boundedcontextB.adapters.out.persistence.Repository.WarehouseEventEntityRepository;
import be.kdg.prog6.boundedcontextB.adapters.out.persistence.Repository.WarehouseEventsWindowEntityRepository;
import be.kdg.prog6.boundedcontextB.domain.*;
import be.kdg.prog6.boundedcontextB.port.in.WarehouseUseCase;
import be.kdg.prog6.boundedcontextB.port.out.Warehouse.WarehouseLoadPort;
import be.kdg.prog6.boundedcontextB.port.out.Warehouse.WarehouseSavePort;
import be.kdg.prog6.boundedcontextB.port.out.WarehouseEvent.WarehouseEventLoadPort;
import be.kdg.prog6.boundedcontextB.port.out.WarehouseEvent.WarehouseEventSavePort;
import be.kdg.prog6.boundedcontextB.port.out.WarehouseEventsWindow.WarehouseEventsWindowLoadPort;
import be.kdg.prog6.boundedcontextB.port.out.WarehouseEventsWindow.WarehouseEventsWindowSavePort;
import org.springframework.stereotype.Service;

@Service
public class WarehouseUseCaseImp implements WarehouseUseCase {


        private final WarehouseEventLoadPort warehouseEventLoadPort;
        private final WarehouseEventSavePort  warehouseEventSavePort;

        private final WarehouseEventsWindowLoadPort warehouseEventsWindowLoadPort;
        private final WarehouseEventsWindowSavePort warehouseEventsWindowSavePort;

        private final WarehouseLoadPort warehouseLoadPort;
        private final WarehouseSavePort warehouseSavePort;

    public WarehouseUseCaseImp(WarehouseEventLoadPort warehouseEventLoadPort, WarehouseEventSavePort warehouseEventSavePort, WarehouseEventsWindowLoadPort warehouseEventsWindowLoadPort, WarehouseEventsWindowSavePort warehouseEventsWindowSavePort, WarehouseLoadPort warehouseLoadPort, WarehouseSavePort warehouseSavePort) {
        this.warehouseEventLoadPort = warehouseEventLoadPort;
        this.warehouseEventSavePort = warehouseEventSavePort;
        this.warehouseEventsWindowLoadPort = warehouseEventsWindowLoadPort;
        this.warehouseEventsWindowSavePort = warehouseEventsWindowSavePort;
        this.warehouseLoadPort = warehouseLoadPort;
        this.warehouseSavePort = warehouseSavePort;
    }

    //note for later maybe when i create the evetn in the warehouse with the 0 as the wgith maybe i should make event that trigger that truck out  using api controller sound better to me ? will aks teacher
    @Override
    public void TruckIn(weighTruckInCommand command) {

        /////////////// very very important when you loead th data in the repo for like the waerhouse you must ensure to load all the child Entites
        //ToDO load the warehouses for the seller based on his id if none was found create one for him
        //TODO but the seller can only have 5 warehouses one for each warehouses so need to check fi the seller dont have two warehouses for the same material
        // if the seller dont have a material for tha MAT create one only if he have < 5
        Warehouse warehouse = new Warehouse();
        warehouse.unloadMaterial(command);
        warehouseSavePort.save(warehouse);

    }
}
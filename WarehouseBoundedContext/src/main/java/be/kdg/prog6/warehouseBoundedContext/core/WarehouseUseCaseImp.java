package be.kdg.prog6.warehouseBoundedContext.core;

import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEntity;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence.Repository.WarehouseRepository;
import be.kdg.prog6.warehouseBoundedContext.domain.*;
import be.kdg.prog6.warehouseBoundedContext.port.in.WarehouseUseCase;
import be.kdg.prog6.warehouseBoundedContext.port.out.Warehouse.WarehouseLoadPort;
import be.kdg.prog6.warehouseBoundedContext.port.out.Warehouse.WarehouseSavePort;
import be.kdg.prog6.warehouseBoundedContext.port.out.WarehouseEvent.WarehouseEventLoadPort;
import be.kdg.prog6.warehouseBoundedContext.port.out.WarehouseEvent.WarehouseEventSavePort;
import be.kdg.prog6.warehouseBoundedContext.port.out.WarehouseEventsWindow.WarehouseEventsWindowLoadPort;
import be.kdg.prog6.warehouseBoundedContext.port.out.WarehouseEventsWindow.WarehouseEventsWindowSavePort;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WarehouseUseCaseImp implements WarehouseUseCase {


    private final WarehouseEventLoadPort warehouseEventLoadPort;
    private final WarehouseEventSavePort warehouseEventSavePort;

    private final WarehouseEventsWindowLoadPort warehouseEventsWindowLoadPort;
    private final WarehouseEventsWindowSavePort warehouseEventsWindowSavePort;

    private final WarehouseLoadPort warehouseLoadPort;
    private final WarehouseSavePort warehouseSavePort;
    private final ModelMapper modelMapper;

    public WarehouseUseCaseImp(WarehouseEventLoadPort warehouseEventLoadPort, WarehouseEventSavePort warehouseEventSavePort, WarehouseEventsWindowLoadPort warehouseEventsWindowLoadPort, WarehouseEventsWindowSavePort warehouseEventsWindowSavePort, WarehouseLoadPort warehouseLoadPort, WarehouseSavePort warehouseSavePort, ModelMapper modelMapper) {
        this.warehouseEventLoadPort = warehouseEventLoadPort;
        this.warehouseEventSavePort = warehouseEventSavePort;
        this.warehouseEventsWindowLoadPort = warehouseEventsWindowLoadPort;
        this.warehouseEventsWindowSavePort = warehouseEventsWindowSavePort;
        this.warehouseLoadPort = warehouseLoadPort;
        this.warehouseSavePort = warehouseSavePort;
        this.modelMapper = modelMapper;
    }

    public Warehouse assignWarehouseToSeller(weighTruckCommand command) {

        switch (command.wareHouseStatus()) {
            case "ALREADY_EXISTS_NOT_FULL" -> {
                return warehouseLoadPort.findBySellerId(command.sellerId())
                        .orElseThrow(() -> new IllegalArgumentException("No warehouse found for this seller."));
            }
            case "CAN_CREATE" -> {
                return new Warehouse(new WarehouseId(UUID.randomUUID()), command.sellerId(), command.materialType());
            }
            default ->
                    throw new IllegalArgumentException("Invalid warehouse status."); // change this later ot something better.
        }
    }


    public void truckOut(weighTruckCommand truckOutCommand) {

        Warehouse warehouse = warehouseLoadPort.findBySellerIdAndMaterialType(truckOutCommand.sellerId(), truckOutCommand.materialType());

        warehouse.updateMaterialWeight(truckOutCommand.weighBridgeTicketId() , truckOutCommand.grossWeight());

        warehouseSavePort.save(warehouse);
        // TODO so i how to send the waerhouse True amount of MAt is in this method
        //TODo after you run the updateMaterialWeight you would call a method in the domain to calculate the MAt
        //TODO by replaying all the events. aned make an event and send it back. that is it

    }

    //note for later maybe when i create the evetn in the warehouse with the 0 as the wgith maybe i should make event that trigger that truck out  using api controller sound better to me ? will aks teacher
    /////////////// very very important when you loead th data in the repo for like the waerhouse you must ensure to load all the child Entites
    @Override
    public void truckIn(weighTruckCommand command) {
        // maybe you need to create a ticket here from asked a teacher because we never saved it so ?
        Warehouse warehouse = assignWarehouseToSeller(command);
        warehouse.beginDeliveryProcess(command);
        warehouseSavePort.save(warehouse);

    }
}
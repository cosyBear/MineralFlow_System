package be.kdg.prog6.warehouseBoundedContext.core;

import be.kdg.prog6.warehouseBoundedContext.domain.*;
import be.kdg.prog6.warehouseBoundedContext.port.in.WarehouseUseCase;
import be.kdg.prog6.warehouseBoundedContext.port.out.EventPublisherPort;
import be.kdg.prog6.warehouseBoundedContext.port.out.Warehouse.WarehouseLoadPort;
import be.kdg.prog6.warehouseBoundedContext.port.out.Warehouse.WarehouseSavePort;
import be.kdg.prog6.warehouseBoundedContext.port.out.WarehouseEvent.WarehouseEventLoadPort;
import be.kdg.prog6.warehouseBoundedContext.port.out.WarehouseEvent.WarehouseEventSavePort;
import be.kdg.prog6.warehouseBoundedContext.port.out.WarehouseEventsWindow.WarehouseEventsWindowLoadPort;
import be.kdg.prog6.warehouseBoundedContext.port.out.WarehouseEventsWindow.WarehouseEventsWindowSavePort;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

//note for later maybe when i create the evetn in the warehouse with the 0 as the wgith maybe i should make event that trigger that truck out  using api controller sound better to me ? will aks teacher
/////////////// very very important when you loead th data in the repo for like the waerhouse you must ensure to load all the child Entites
// TODO so i how to send the waerhouse True amount of MAt is in this method
//TODo after you run the updateMaterialWeight you would call a method in the domain to calculate the MAt
//TODO by replaying all the events. aned make an event and send it back. that is it
@Service
public class WarehouseUseCaseImp implements WarehouseUseCase {

    private static final Logger logger = LogManager.getLogger(WarehouseUseCaseImp.class);

    private final WarehouseEventLoadPort warehouseEventLoadPort;
    private final WarehouseEventSavePort warehouseEventSavePort;

    private final WarehouseEventsWindowLoadPort warehouseEventsWindowLoadPort;
    private final WarehouseEventsWindowSavePort warehouseEventsWindowSavePort;

    private final WarehouseLoadPort warehouseLoadPort;
    private final WarehouseSavePort warehouseSavePort;
    private final ModelMapper modelMapper;

    EventPublisherPort publisherPort;

    public WarehouseUseCaseImp(WarehouseEventLoadPort warehouseEventLoadPort, WarehouseEventSavePort warehouseEventSavePort, WarehouseEventsWindowLoadPort warehouseEventsWindowLoadPort, WarehouseEventsWindowSavePort warehouseEventsWindowSavePort, WarehouseLoadPort warehouseLoadPort, WarehouseSavePort warehouseSavePort, ModelMapper modelMapper, EventPublisherPort publisherPort) {
        this.warehouseEventLoadPort = warehouseEventLoadPort;
        this.warehouseEventSavePort = warehouseEventSavePort;
        this.warehouseEventsWindowLoadPort = warehouseEventsWindowLoadPort;
        this.warehouseEventsWindowSavePort = warehouseEventsWindowSavePort;
        this.warehouseLoadPort = warehouseLoadPort;
        this.warehouseSavePort = warehouseSavePort;
        this.modelMapper = modelMapper;
        this.publisherPort = publisherPort;
    }

    public Warehouse assignWarehouseToSeller(SellerId sellerId , MaterialType materialType , String wareHouseStatus) {

        switch (wareHouseStatus) {
            case "ALREADY_EXISTS_NOT_FULL" -> {
                return warehouseLoadPort.findBySellerIdAndMaterialType(sellerId , materialType);
            }
            case "CAN_CREATE" -> {
                return new Warehouse(new WarehouseId(UUID.randomUUID()), sellerId, materialType);
            }
            default ->
                    throw new IllegalArgumentException("Invalid warehouse status."); // change this later ot something better.
        }
    }
    // maybe you need to create a ticket here from asked a teacher because we never saved it so ?


    @Override
    @Transactional
    public void truckOut(WeighTruckOutCommand truckOutCommand) {

        Warehouse warehouse = assignWarehouseToSeller(truckOutCommand.sellerId() , truckOutCommand.materialType() , truckOutCommand.wareHouseStatus());

        warehouse.updateMaterialWeight(truckOutCommand );

        warehouseSavePort.save(warehouse);

        WarehouseMaterialEvent warehouseMaterialEvent = new WarehouseMaterialEvent(warehouse.getWarehouseNumber().getId() ,warehouse.getCurrentLoadOfWarehouse() ,  warehouse.getMaterialType() , warehouse.getSellerId().getSellerID());
        // in here created the WarehouseMaterialEvent and publish it. with the update stuff
        //look at the WarehouseMaterialEvent object to know what to send.


        publisherPort.publishWarehouseMaterialEvent(warehouseMaterialEvent);




    }


    @Override
    public void truckIn(WeighTruckInCommand command) {
        Warehouse warehouse = assignWarehouseToSeller(command.sellerId() , command.materialType() , command.wareHouseStatus());
        warehouse.beginDeliveryProcess(command);
        logger.info("PDT: type of material {} , date of delivery {} , warehouse number {}",
                command.getMaterialType(), command.getWeighInTime(), warehouse.getWarehouseNumber());
        warehouseSavePort.save(warehouse);




    }
}
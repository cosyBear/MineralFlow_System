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

    public Warehouse assignWarehouseToSeller(SellerId sellerId, MaterialType materialType, String wareHouseStatus) {

        switch (wareHouseStatus) {
            case "ALREADY_EXISTS_NOT_FULL" -> {
                return warehouseLoadPort.findBySellerIdAndMaterialType(sellerId, materialType);
            }
            case "CAN_CREATE" -> {
                return new Warehouse(new WarehouseId(UUID.randomUUID()), sellerId, materialType);
            }
            default ->
                    throw new IllegalArgumentException("Invalid warehouse status."); // change this later ot something better.
        }
    }


    @Override
    @Transactional
    public void truckOut(WeighTruckOutCommand truckOutCommand) {

        Warehouse warehouse = assignWarehouseToSeller(truckOutCommand.sellerId(), truckOutCommand.materialType(), truckOutCommand.wareHouseStatus());

        warehouse.updateMaterialWeight(truckOutCommand);

        warehouseSavePort.save(warehouse);

        WarehouseMaterialEvent warehouseMaterialEvent = new WarehouseMaterialEvent(warehouse.getWarehouseNumber().getId(), warehouse.getCurrentLoadOfWarehouse(), warehouse.getMaterialType(), warehouse.getSellerId().getSellerID());


        publisherPort.publishWarehouseMaterialEvent(warehouseMaterialEvent);


    }


    @Override
    public void truckIn(WeighTruckInCommand command) {
        Warehouse warehouse = assignWarehouseToSeller(command.sellerId(), command.materialType(), command.wareHouseStatus());
        warehouse.beginDeliveryProcess(command);
        logger.info("PDT: type of material {} , date of delivery {} , warehouse number {}",
                command.getMaterialType(), command.getWeighInTime(), warehouse.getWarehouseNumber());
        warehouseSavePort.save(warehouse);


    }
}
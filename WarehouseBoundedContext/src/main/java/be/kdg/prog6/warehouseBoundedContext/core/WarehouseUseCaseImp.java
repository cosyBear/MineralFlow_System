package be.kdg.prog6.warehouseBoundedContext.core;

import be.kdg.prog6.warehouseBoundedContext.domain.*;
import be.kdg.prog6.warehouseBoundedContext.port.in.WarehouseUseCase;
import be.kdg.prog6.warehouseBoundedContext.port.in.WeighTruckInCommand;
import be.kdg.prog6.warehouseBoundedContext.port.in.WeighTruckOutCommand;
import be.kdg.prog6.warehouseBoundedContext.port.out.Warehouse.WarehouseLoadPort;
import be.kdg.prog6.warehouseBoundedContext.port.out.Warehouse.WarehouseSavePort;
import be.kdg.prog6.warehouseBoundedContext.port.out.WarehouseEvent.WarehouseEventLoadPort;
import be.kdg.prog6.warehouseBoundedContext.port.out.WarehouseEvent.WarehouseEventSavePort;
import be.kdg.prog6.warehouseBoundedContext.port.out.WarehouseEventsWindow.WarehouseEventsWindowLoadPort;
import be.kdg.prog6.warehouseBoundedContext.port.out.WarehouseEventsWindow.WarehouseEventsWindowSavePort;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class WarehouseUseCaseImp implements WarehouseUseCase {

    private static final Logger logger = LogManager.getLogger(WarehouseUseCaseImp.class);

    private final WarehouseEventLoadPort warehouseEventLoadPort;
    private final WarehouseEventSavePort warehouseEventSavePort;

    private final WarehouseEventsWindowLoadPort warehouseEventsWindowLoadPort;
    private final WarehouseEventsWindowSavePort warehouseEventsWindowSavePort;

    private final WarehouseLoadPort warehouseLoadPort;
    private final ModelMapper modelMapper;

   private final  List<WarehouseSavePort> warehouseSavePorts;

    public WarehouseUseCaseImp(WarehouseEventLoadPort warehouseEventLoadPort, WarehouseEventSavePort warehouseEventSavePort, WarehouseEventsWindowLoadPort warehouseEventsWindowLoadPort, WarehouseEventsWindowSavePort warehouseEventsWindowSavePort, WarehouseLoadPort warehouseLoadPort, @Qualifier("warehouse") ModelMapper modelMapper, List<WarehouseSavePort> warehouseSavePorts) {
        this.warehouseEventLoadPort = warehouseEventLoadPort;
        this.warehouseEventSavePort = warehouseEventSavePort;
        this.warehouseEventsWindowLoadPort = warehouseEventsWindowLoadPort;
        this.warehouseEventsWindowSavePort = warehouseEventsWindowSavePort;
        this.warehouseLoadPort = warehouseLoadPort;
        this.modelMapper = modelMapper;
        this.warehouseSavePorts = warehouseSavePorts;
    }

    @Override
    @Transactional
    public void truckOut(WeighTruckOutCommand truckOutCommand) {
        Warehouse warehouse = warehouseLoadPort.findBySellerIdAndMaterialType(truckOutCommand.sellerId(), truckOutCommand.materialType());
        final WarehouseEvent event  = warehouse.deliveryMaterial(truckOutCommand.getWeighOutTime() , truckOutCommand.getMaterialTrueWeight() , truckOutCommand.weighBridgeTicketId() , truckOutCommand.getMaterialType());
        logger.info("WTFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
        logger.info("why is you not working plssssssssssssssssssssssss tellll meeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        warehouseSavePorts.forEach(savePort -> savePort.save(warehouse, event));
    }


    @Override
    public void truckIn(WeighTruckInCommand command) {
        Warehouse warehouse = warehouseLoadPort.findBySellerIdAndMaterialType(command.sellerId(), command.materialType());
        // this is doing nothing
        // so maybe creat a PDT or something .......
        //warehouse.beginDeliveryProcess(command);
        //maybe create a pdt here
        logger.info("PDT: type of material {} , date of delivery {} , warehouse number {}",
                command.getMaterialType(), command.getWeighInTime(), warehouse.getWarehouseNumber());



    }
}
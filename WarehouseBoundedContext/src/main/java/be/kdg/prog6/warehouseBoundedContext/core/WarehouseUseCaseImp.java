package be.kdg.prog6.warehouseBoundedContext.core;

import be.kdg.prog6.warehouseBoundedContext.domain.*;
import be.kdg.prog6.warehouseBoundedContext.port.in.WarehouseUseCase;
import be.kdg.prog6.warehouseBoundedContext.port.in.WeighTruckInCommand;
import be.kdg.prog6.warehouseBoundedContext.port.in.WeighTruckOutCommand;
import be.kdg.prog6.warehouseBoundedContext.port.out.WarehouseLoadPort;
import be.kdg.prog6.warehouseBoundedContext.port.out.WarehouseSavePort;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class WarehouseUseCaseImp implements WarehouseUseCase {

    private static final Logger logger = LogManager.getLogger(WarehouseUseCaseImp.class);


    private final WarehouseLoadPort warehouseLoadPort;

    private final List<WarehouseSavePort> warehouseSavePorts;

    public WarehouseUseCaseImp(WarehouseLoadPort warehouseLoadPort, List<WarehouseSavePort> warehouseSavePorts) {
        this.warehouseLoadPort = warehouseLoadPort;
        this.warehouseSavePorts = warehouseSavePorts;
    }


    @Override
    @Transactional
    public void truckOut(WeighTruckOutCommand truckOutCommand) {
        Warehouse warehouse = warehouseLoadPort.findBySellerIdAndMaterialType(truckOutCommand.sellerId(), truckOutCommand.materialType());
        final WarehouseEvent event = warehouse.deliveryMaterial(truckOutCommand.getWeighOutTime(), truckOutCommand.getMaterialTrueWeight(), truckOutCommand.weighBridgeTicketId(), truckOutCommand.getMaterialType());
        warehouseSavePorts.forEach(savePort -> savePort.save(warehouse, event));
    }


    @Override
    public void truckIn(WeighTruckInCommand command) {
        Warehouse warehouse = warehouseLoadPort.findBySellerIdAndMaterialType(command.sellerId(), command.materialType());
        // teacher said remove you imp here you dont need it.
        // and its ok to keep it empty like this.
        logger.info("PDT: type of material {} , date of delivery {} , warehouse number {}",
                command.getMaterialType(), command.getWeighInTime(), warehouse.getWarehouseNumber());
    }
}
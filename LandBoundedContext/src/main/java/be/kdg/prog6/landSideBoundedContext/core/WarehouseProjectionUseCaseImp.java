package be.kdg.prog6.landSideBoundedContext.core;

import be.kdg.prog6.landSideBoundedContext.port.in.UpdateWarehouseCommand;
import be.kdg.prog6.landSideBoundedContext.domain.Warehouse;
import be.kdg.prog6.landSideBoundedContext.port.in.WarehouseProjectionUseCase;
import be.kdg.prog6.landSideBoundedContext.port.out.WarehouseLoadPort;
import be.kdg.prog6.landSideBoundedContext.port.out.WarehouseSavePort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WarehouseProjectionUseCaseImp implements WarehouseProjectionUseCase {
    private final WarehouseLoadPort warehouseLoadPort;
    private final WarehouseSavePort warehouseSavePort;

    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseProjectionUseCaseImp.class);

    public WarehouseProjectionUseCaseImp(WarehouseLoadPort warehouseLoadPort, WarehouseSavePort warehouseSavePort) {
        this.warehouseLoadPort = warehouseLoadPort;
        this.warehouseSavePort = warehouseSavePort;
    }

    @Override
    @Transactional
    public void updateWarehouse(UpdateWarehouseCommand updateWarehouseCommand) {
        Warehouse warehouse = warehouseLoadPort.findBySellerIdAAndMaterialType(updateWarehouseCommand.sellerId(), updateWarehouseCommand.materialType());
        warehouse.updateMaterial(updateWarehouseCommand.amount());
        LOGGER.info("Warehouse is update: the warehouse current  available Capacity {} " ,  warehouse.availableCapacity());
        warehouseSavePort.Save(warehouse);
    }
}
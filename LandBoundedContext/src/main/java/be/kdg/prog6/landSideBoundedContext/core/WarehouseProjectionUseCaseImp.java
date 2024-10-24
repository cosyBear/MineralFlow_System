package be.kdg.prog6.landSideBoundedContext.core;

import be.kdg.prog6.landSideBoundedContext.port.in.UpdateWarehouseCommand;
import be.kdg.prog6.landSideBoundedContext.domain.Warehouse;
import be.kdg.prog6.landSideBoundedContext.port.in.WarehouseProjectionUseCase;
import be.kdg.prog6.landSideBoundedContext.port.out.WarehouseLoadPort;
import be.kdg.prog6.landSideBoundedContext.port.out.WarehouseSavePort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class WarehouseProjectionUseCaseImp implements WarehouseProjectionUseCase {
    private final WarehouseLoadPort warehouseLoadPort;
    private final WarehouseSavePort warehouseSavePort;

    public WarehouseProjectionUseCaseImp(WarehouseLoadPort warehouseLoadPort, WarehouseSavePort warehouseSavePort) {
        this.warehouseLoadPort = warehouseLoadPort;
        this.warehouseSavePort = warehouseSavePort;
    }

    @Override
    @Transactional
    public void updateWarehouse(UpdateWarehouseCommand updateWarehouseCommand) {
        Warehouse warehouse = warehouseLoadPort.findBySellerIdAAndMaterialType(updateWarehouseCommand.sellerId(), updateWarehouseCommand.materialType());
        warehouse.updateWarehouse(updateWarehouseCommand.amount());
        warehouseSavePort.Save(warehouse);
    }
}
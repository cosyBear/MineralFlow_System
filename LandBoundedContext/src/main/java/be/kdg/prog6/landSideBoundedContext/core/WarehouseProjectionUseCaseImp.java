package be.kdg.prog6.landSideBoundedContext.core;

import be.kdg.prog6.landSideBoundedContext.domain.UpdateWarehouseCommand;
import be.kdg.prog6.landSideBoundedContext.domain.Warehouse;
import be.kdg.prog6.landSideBoundedContext.domain.WarehouseAction;
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
        Warehouse warehouse = warehouseLoadPort.findById(updateWarehouseCommand.warehouseId().warehouseId())
                .orElseGet(() -> new Warehouse(
                        updateWarehouseCommand.warehouseId(),
                        updateWarehouseCommand.sellerId(),
                        updateWarehouseCommand.materialType()
                ));

        if (updateWarehouseCommand.action() == WarehouseAction.ADD) {
            warehouse.addMaterial(updateWarehouseCommand.amount());
        } else if (updateWarehouseCommand.action() == WarehouseAction.SUBTRACT) {
            warehouse.subtractMaterial(updateWarehouseCommand.amount());
        }
        warehouseSavePort.Save(warehouse);
    }
}
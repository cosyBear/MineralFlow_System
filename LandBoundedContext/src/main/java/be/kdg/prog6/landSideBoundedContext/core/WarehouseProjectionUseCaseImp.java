package be.kdg.prog6.landSideBoundedContext.core;

import be.kdg.prog6.landSideBoundedContext.domain.UpdateWarehouseCommand;
import be.kdg.prog6.landSideBoundedContext.domain.WareHouse;
import be.kdg.prog6.landSideBoundedContext.port.in.WarehouseProjectionUseCase;
import be.kdg.prog6.landSideBoundedContext.port.out.WarehouseLoadPort;
import be.kdg.prog6.landSideBoundedContext.port.out.WarehouseSavePort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WarehouseProjectionUseCaseImp implements WarehouseProjectionUseCase {
    private final WarehouseLoadPort warehouseLoadPort;
    private final WarehouseSavePort warehouseSavePort;

    public WarehouseProjectionUseCaseImp(WarehouseLoadPort warehouseLoadPort, WarehouseSavePort warehouseSavePort) {
        this.warehouseLoadPort = warehouseLoadPort;
        this.warehouseSavePort = warehouseSavePort;
    }

    // create other use case called warehouse projection  Usecase
    @Override
    @Transactional
    public void updateWarehouse(UpdateWarehouseCommand updateWarehouseCommand) {
        Optional<WareHouse> existingWarehouse = warehouseLoadPort.findById(updateWarehouseCommand.warehouseId().warehouseId());

        // If the warehouse is not found, create a new one
        if (existingWarehouse.isEmpty()) {
            WareHouse wareHouse = new WareHouse(
                    updateWarehouseCommand.warehouseId(),
                    updateWarehouseCommand.sellerId(),
                    updateWarehouseCommand.materialType(),
                    updateWarehouseCommand.materialAmountInWarehouse()
            );
            warehouseSavePort.Save(wareHouse);
        } else {
            // If the warehouse is found, update it
            WareHouse wareHouse = existingWarehouse.get();
            wareHouse.updateWarehouseMaterialAmount(updateWarehouseCommand.materialAmountInWarehouse());
            System.out.println(wareHouse.getAmountOfMaterial());
            warehouseSavePort.Save(wareHouse);
        }
    }



}
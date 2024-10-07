package be.kdg.prog6.landSideBoundedContext.adapters.in;


import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import be.kdg.prog6.landSideBoundedContext.domain.Id.WarehouseId;
import be.kdg.prog6.landSideBoundedContext.domain.MaterialType;
import be.kdg.prog6.landSideBoundedContext.domain.UpdateWarehouseCommand;
import be.kdg.prog6.landSideBoundedContext.dto.WarehouseDto;
import be.kdg.prog6.landSideBoundedContext.port.in.WarehouseProjectionUseCase;
import be.kdg.prog6.landSideBoundedContext.port.in.WeighBridgeUseCase;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class WarehouseListener {


    private final WarehouseProjectionUseCase warehouseProjectionUseCase;

    public WarehouseListener(WarehouseProjectionUseCase warehouseProjectionUseCase) {
        this.warehouseProjectionUseCase = warehouseProjectionUseCase;
    }


    @RabbitListener(queues = "WarehouseMaterial_QUEUE")
    public void listenToWarehouse(WarehouseDto dto){

        UpdateWarehouseCommand updateWarehouseCommand = new UpdateWarehouseCommand(new WarehouseId(UUID.fromString(dto.warehouseId())) , dto.materialAmountInWarehouse(), MaterialType.valueOf(dto.materialType()), new SellerId(UUID.fromString(dto.sellerId())));

        warehouseProjectionUseCase.updateWarehouse(updateWarehouseCommand);
    }







}

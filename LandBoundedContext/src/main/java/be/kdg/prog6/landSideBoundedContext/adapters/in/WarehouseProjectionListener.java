package be.kdg.prog6.landSideBoundedContext.adapters.in;


import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import be.kdg.prog6.landSideBoundedContext.domain.Id.WarehouseId;
import be.kdg.prog6.landSideBoundedContext.domain.UpdateWarehouseCommand;
import be.kdg.prog6.landSideBoundedContext.adapters.in.dto.WarehouseDto;
import be.kdg.prog6.landSideBoundedContext.domain.WarehouseAction;
import be.kdg.prog6.landSideBoundedContext.port.in.WarehouseProjectionUseCase;
import domain.MaterialType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WarehouseProjectionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseProjectionListener.class);

    private final WarehouseProjectionUseCase warehouseProjectionUseCase;

    public WarehouseProjectionListener(WarehouseProjectionUseCase warehouseProjectionUseCase) {
        this.warehouseProjectionUseCase = warehouseProjectionUseCase;
    }


    @RabbitListener(queues = "WarehouseMaterial_QUEUE")
    public void listenToWarehouse(WarehouseDto dto) {
        UpdateWarehouseCommand updateWarehouseCommand = new UpdateWarehouseCommand(
                new WarehouseId(dto.warehouseId()), dto.materialAmountInWarehouse(),
                MaterialType.valueOf(dto.materialType()), new SellerId(dto.sellerId()));

        warehouseProjectionUseCase.updateWarehouse(updateWarehouseCommand);
    }


}

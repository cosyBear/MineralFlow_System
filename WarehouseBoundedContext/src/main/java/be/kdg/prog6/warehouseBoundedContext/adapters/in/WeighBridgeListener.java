package be.kdg.prog6.warehouseBoundedContext.adapters.in;
import be.kdg.prog6.warehouseBoundedContext.domain.MaterialType;
import be.kdg.prog6.warehouseBoundedContext.domain.SellerId;
import be.kdg.prog6.warehouseBoundedContext.domain.WeighTruckInCommand;
import be.kdg.prog6.warehouseBoundedContext.domain.WeighTruckOutCommand;
import be.kdg.prog6.warehouseBoundedContext.dto.WeighInTruckDto;
import be.kdg.prog6.warehouseBoundedContext.dto.WeighOutTruckDto;
import be.kdg.prog6.warehouseBoundedContext.port.in.WarehouseUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Component;

@Component
public class WeighBridgeListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeighBridgeListener.class);

    private static final String truckWeightedInQueue = "truckWeightedInQueue";
    private static final String truckWeightedOutQueue = "truckWeightedOutQueue";
    private final WarehouseUseCase wareHouseUseCase;

    public WeighBridgeListener(WarehouseUseCase warehouseUseCase ) {
        this.wareHouseUseCase = warehouseUseCase;
    }


    @RabbitListener(queues = truckWeightedInQueue)
    public void truckWeightedIn(WeighInTruckDto truckOutDto ) {

        WeighTruckInCommand command = new WeighTruckInCommand(
                truckOutDto.weighBridgeTicketId(),
                truckOutDto.licensePlate(),
                new SellerId(truckOutDto.sellerId()),  // Create SellerId manually from UUID
                truckOutDto.weight(),
                MaterialType.valueOf(truckOutDto.materialType()),
                truckOutDto.weighTime(),
                truckOutDto.warehouseStatus()
        );
        wareHouseUseCase.truckIn(command);
    }



    @RabbitListener(queues = truckWeightedOutQueue)
    public void truckWeightedOut(WeighOutTruckDto truckOutDto ) {

        WeighTruckOutCommand command = new WeighTruckOutCommand(
                truckOutDto.weighBridgeTicketId(),
                truckOutDto.licensePlate(),
                new SellerId(truckOutDto.sellerId()),
                truckOutDto.materialTrueWeight(),
                MaterialType.valueOf(truckOutDto.materialType()),
                truckOutDto.weighOutTime(),
                truckOutDto.warehouseStatus()
        );

        wareHouseUseCase.truckOut(command);

    }




}

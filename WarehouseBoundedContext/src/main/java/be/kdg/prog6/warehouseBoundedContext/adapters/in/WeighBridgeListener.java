



package be.kdg.prog6.warehouseBoundedContext.adapters.in;
import be.kdg.prog6.warehouseBoundedContext.domain.SellerId;
import be.kdg.prog6.warehouseBoundedContext.domain.WeighTruckInCommand;
import be.kdg.prog6.warehouseBoundedContext.domain.WeighTruckOutCommand;
import be.kdg.prog6.warehouseBoundedContext.dto.WeighInTruckDto;
import be.kdg.prog6.warehouseBoundedContext.dto.WeighOutTruckDto;
import be.kdg.prog6.warehouseBoundedContext.port.in.WarehouseUseCase;
import domain.MaterialType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
                new SellerId(truckOutDto.sellerId()),
                truckOutDto.grossWeight(),
                MaterialType.valueOf(truckOutDto.materialType()),
                truckOutDto.weighInTime()
        );
        wareHouseUseCase.truckIn(command);
    }



    @RabbitListener(queues = truckWeightedOutQueue)
    public void truckWeightedOut(WeighOutTruckDto truckOutDto ) {

        WeighTruckOutCommand command = new WeighTruckOutCommand(
                truckOutDto.weighBridgeTicketId(),
                truckOutDto.licensePlate(),
                new SellerId(truckOutDto.sellerId()),
                truckOutDto.endWeight(),
                MaterialType.valueOf(truckOutDto.materialType()),
                truckOutDto.weighOutTime()
        );

        wareHouseUseCase.truckOut(command);

    }

}

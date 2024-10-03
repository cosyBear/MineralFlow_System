package be.kdg.prog6.warehouseBoundedContext.adapters.in;
import be.kdg.prog6.warehouseBoundedContext.domain.MaterialType;
import be.kdg.prog6.warehouseBoundedContext.domain.SellerId;
import be.kdg.prog6.warehouseBoundedContext.domain.weighTruckCommand;
import be.kdg.prog6.warehouseBoundedContext.domain.weighTruckOutCommand;
import be.kdg.prog6.warehouseBoundedContext.dto.WeighTruckDto;
import be.kdg.prog6.warehouseBoundedContext.port.in.WarehouseUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class WeighBridgeListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeighBridgeListener.class);

    private static final String truckWeightedInQueue = "truckWeightedInQueue";
    private static final String truckWeightedOutQueue = "truckWeightedOutQueue";
    private final WarehouseUseCase wareHouseUseCase;

    public WeighBridgeListener(WarehouseUseCase warehouseUseCase) {
        this.wareHouseUseCase = warehouseUseCase;
    }


    @RabbitListener(queues = truckWeightedInQueue)
    public void truckWeightedIn(WeighTruckDto truckOutDto ) {
        System.out.println(truckOutDto.weighBridgeTicketId());
        System.out.println(truckOutDto.sellerId());
        System.out.println(truckOutDto.licensePlate());
        weighTruckCommand command = new weighTruckCommand(UUID.fromString(truckOutDto.weighBridgeTicketId()) , truckOutDto.licensePlate(), new SellerId(UUID.fromString(truckOutDto.sellerId())), truckOutDto.weight(), MaterialType.valueOf(truckOutDto.materialType()), truckOutDto.weighTime(),
                truckOutDto.warehouseStatus());

        wareHouseUseCase.truckIn(command);

    }



    @RabbitListener(queues = truckWeightedOutQueue)
    public void truckWeightedOut(WeighTruckDto truckOutDto ) {
        System.out.println("PENIS");
        weighTruckCommand command = new weighTruckCommand(UUID.fromString(truckOutDto.weighBridgeTicketId()) , truckOutDto.licensePlate(), new SellerId(UUID.fromString(truckOutDto.sellerId())), truckOutDto.weight(), MaterialType.valueOf(truckOutDto.materialType()), truckOutDto.weighTime(),
                truckOutDto.warehouseStatus());
        wareHouseUseCase.truckOut(command);

    }




}

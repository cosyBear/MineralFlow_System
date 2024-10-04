package be.kdg.prog6.warehouseBoundedContext.adapters.in;
import be.kdg.prog6.warehouseBoundedContext.domain.MaterialType;
import be.kdg.prog6.warehouseBoundedContext.domain.SellerId;
import be.kdg.prog6.warehouseBoundedContext.domain.WeighTruckCommand;
import be.kdg.prog6.warehouseBoundedContext.dto.WeighTruckDto;
import be.kdg.prog6.warehouseBoundedContext.port.in.WarehouseUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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

        System.out.println("sellerId in truckOutDto: " + truckOutDto.sellerId());
        try {
            UUID sellerUuid = truckOutDto.sellerId();
            SellerId sellerId = new SellerId(sellerUuid);
        } catch (IllegalArgumentException e) {
            // Handle the invalid UUID format here (e.g., throw a custom exception or log the error)
            System.err.println("Invalid UUID format for sellerId: " + truckOutDto.sellerId());
        }

        WeighTruckCommand command = new WeighTruckCommand(
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
    public void truckWeightedOut(WeighTruckDto truckOutDto ) {
        System.out.println("PENIS");
        WeighTruckCommand command = new WeighTruckCommand(
                truckOutDto.weighBridgeTicketId(),
                truckOutDto.licensePlate(),
                new SellerId(truckOutDto.sellerId()),
                truckOutDto.weight(),
                MaterialType.valueOf(truckOutDto.materialType()),
                truckOutDto.weighTime(),
                truckOutDto.warehouseStatus()
        );

        wareHouseUseCase.truckOut(command);

    }




}

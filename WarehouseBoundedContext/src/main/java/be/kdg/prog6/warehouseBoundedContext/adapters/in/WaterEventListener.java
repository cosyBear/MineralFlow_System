package be.kdg.prog6.warehouseBoundedContext.adapters.in;
import be.kdg.prog6.warehouseBoundedContext.adapters.dto.ShipmentDto;
import be.kdg.prog6.warehouseBoundedContext.port.in.ShipmentCommand;

import java.util.UUID;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import be.kdg.prog6.warehouseBoundedContext.port.in.*;
@Component
public class WaterEventListener {


    private static final String shipmentQueue  = "shipmentQueue";

    private ShipmentOrderUseCase shipmentOrderUseCase;

    public void waterSideListener(ShipmentOrderUseCase shipUseCase ){
        this.shipmentOrderUseCase = shipUseCase;
    }





    @RabbitListener(queues = shipmentQueue)
    public void shipmentIn(ShipmentDto dto){
        System.out.println("this is the dto {}" + dto);
        ShipmentCommand ShipmentCommand = new ShipmentCommand(dto.purchaseOrder() ,dto.vesselNumber(), dto.arrivalTime(),dto.departureTime());
        shipmentOrderUseCase.shipmentIn(ShipmentCommand);

    }









}

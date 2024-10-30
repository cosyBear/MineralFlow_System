package be.kdg.prog6.warehouseBoundedContext.adapters.in;
import be.kdg.prog6.warehouseBoundedContext.adapters.dto.ShipmentDto;
import be.kdg.prog6.warehouseBoundedContext.port.in.ShipmentCommand;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import be.kdg.prog6.warehouseBoundedContext.port.in.*;
@Component
public class ShipInAmqpAdapter {


    private static final String shipInQueue  = "shipInQueue";

    private final ShipmentOrderUseCase shipmentOrderUseCase;

    public ShipInAmqpAdapter(ShipmentOrderUseCase shipmentOrderUseCase) {
        this.shipmentOrderUseCase = shipmentOrderUseCase;
    }


    @RabbitListener(queues = shipInQueue )
    public void shipmentIn(ShipmentDto dto){
        ShipmentCommand ShipmentCommand = new ShipmentCommand(dto.purchaseOrder() ,dto.vesselNumber(), dto.arrivalTime());
        shipmentOrderUseCase.shipmentIn(ShipmentCommand);
    }
}

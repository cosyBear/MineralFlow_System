package be.kdg.prog6.watersideboundedcontext.adapters.in;


import be.kdg.prog6.watersideboundedcontext.adapters.dto.ShipmentCompletedDto;
import be.kdg.prog6.watersideboundedcontext.domain.ShipmentCompletedCommand;
import be.kdg.prog6.watersideboundedcontext.domain.ShipmentOrder;
import be.kdg.prog6.watersideboundedcontext.port.in.ShipmentOrderUseCase;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ShipmentEventListener {

    private final ShipmentOrderUseCase useCase;

    public ShipmentEventListener(ShipmentOrderUseCase useCase) {
        this.useCase = useCase;
    }


    @RabbitListener(queues = "shipOutQueue")
    public void shipmentCompleted(ShipmentCompletedDto order ) {

        ShipmentCompletedCommand command = new ShipmentCompletedCommand(order.purchaseOrderId(),order.vesselNumber(),order.completionTime());

        if(useCase.shipDeparture(command) ){
            System.out.println("Ship can leave pookie");

        }else
            System.out.println("Ship cannot leave pookie");

    }
}

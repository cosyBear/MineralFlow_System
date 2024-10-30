package be.kdg.prog6.watersideboundedcontext.adapters.in;


import be.kdg.prog6.watersideboundedcontext.adapters.dto.ShipmentCompletedDto;
import be.kdg.prog6.watersideboundedcontext.port.in.ShipmentCompletedCommand;
import be.kdg.prog6.watersideboundedcontext.port.in.ShipmentOrderUseCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ShipmentCompletedAmqpAdapter {

    private final ShipmentOrderUseCase useCase;

    public ShipmentCompletedAmqpAdapter(ShipmentOrderUseCase useCase) {
        this.useCase = useCase;
    }

    private static final Logger logger = LogManager.getLogger(ShipmentCompletedAmqpAdapter.class);


    @RabbitListener(queues = "shipOutQueue" )
    public void shipmentCompleted(ShipmentCompletedDto order) {
        ShipmentCompletedCommand command = new ShipmentCompletedCommand(order.purchaseOrderId(), order.vesselNumber(), order.completionTime());
        logger.info("the ship is loaded with all the materials");
        useCase.shipDeparture(command);


    }
}

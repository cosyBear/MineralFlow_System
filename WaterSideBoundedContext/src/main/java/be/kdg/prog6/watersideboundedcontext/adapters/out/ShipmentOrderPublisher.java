package be.kdg.prog6.watersideboundedcontext.adapters.out;

import be.kdg.prog6.watersideboundedcontext.domain.ShipmentOrder;
import be.kdg.prog6.watersideboundedcontext.domain.ShipmentOrderEvent;
import be.kdg.prog6.watersideboundedcontext.port.out.ShipmentOrderSavePort;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class ShipmentOrderPublisher implements ShipmentOrderSavePort {

    private static final Logger logger = LogManager.getLogger(ShipmentOrderPublisher.class);
    private final RabbitTemplate rabbitTemplate;

    public ShipmentOrderPublisher(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    private static String WaterSideExchange = "waterSideExchange";


    @Override
    public void Save(ShipmentOrder order) {
        String routingKey = "ship." + order.getPurchaseOrder();
        ShipmentOrderEvent event = new ShipmentOrderEvent(order.getPurchaseOrder(),
                order.getVesselNumber(),
                order.getArrivalTime(),
                order.getDepartureTime());

        rabbitTemplate.convertAndSend(WaterSideExchange, routingKey, event);

    }
}

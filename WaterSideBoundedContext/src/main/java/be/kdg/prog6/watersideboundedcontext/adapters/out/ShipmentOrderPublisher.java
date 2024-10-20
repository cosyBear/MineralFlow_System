package be.kdg.prog6.watersideboundedcontext.adapters.out;

import be.kdg.prog6.watersideboundedcontext.domain.RequestMaterialEvent;
import be.kdg.prog6.watersideboundedcontext.domain.ShipmentOrder;
import be.kdg.prog6.watersideboundedcontext.port.out.ShipmentOrderEventPublisher;
import be.kdg.prog6.watersideboundedcontext.port.out.ShipmentOrderSavePort;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class ShipmentOrderPublisher implements ShipmentOrderEventPublisher {

    private static final Logger logger = LogManager.getLogger(ShipmentOrderPublisher.class);
    private final RabbitTemplate rabbitTemplate;

    public ShipmentOrderPublisher(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    private static String WaterSideExchange = "waterSideExchange";

    @Override
    public void requestMaterialEvent(RequestMaterialEvent event ) {
        String routingKey = "ship." + event.purchaseOrder() + "out";
        RequestMaterialEvent requestEvent  = new RequestMaterialEvent(event.purchaseOrder() ,
                event.vesselNumber(),
                event.arrivalTime());
        rabbitTemplate.convertAndSend(WaterSideExchange, routingKey, event);

    }

}

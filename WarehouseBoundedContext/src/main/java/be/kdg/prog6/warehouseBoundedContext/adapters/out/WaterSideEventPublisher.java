package be.kdg.prog6.warehouseBoundedContext.adapters.out;

import be.kdg.prog6.warehouseBoundedContext.port.out.ShipmentCompletedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class WaterSideEventPublisher implements be.kdg.prog6.warehouseBoundedContext.port.out.WaterSideEventPublisher
{
    private static final Logger LOGGER = LoggerFactory.getLogger(WaterSideEventPublisher.class);

    private final RabbitTemplate rabbitTemplate;

    public WaterSideEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }



    @Override
    public void ShipmentCompleted(ShipmentCompletedEvent event) {
        String key = "ship." + event.purchaseOrderId() + ".out";
        LOGGER.info("the ship has been loaded with material");

        rabbitTemplate.convertAndSend("waterSideExchange", key, event);
    }
}

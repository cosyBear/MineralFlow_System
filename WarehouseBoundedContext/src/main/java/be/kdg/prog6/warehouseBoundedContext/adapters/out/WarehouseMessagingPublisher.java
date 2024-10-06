package be.kdg.prog6.warehouseBoundedContext.adapters.out;

import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseMaterialEvent;
import be.kdg.prog6.warehouseBoundedContext.port.out.EventPublisherPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class WarehouseMessagingPublisher implements EventPublisherPort {
    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseMessagingPublisher.class);

    private final RabbitTemplate rabbitTemplate;

    public WarehouseMessagingPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @Override
    public void publishWarehouseMaterialEvent(WarehouseMaterialEvent warehouseMaterialEvent) {

        LOGGER.info("notifying  the landSide about the material amount in the warehouse");
        rabbitTemplate.convertAndSend("WarehouseExchange", "WarehouseRoutingKey", warehouseMaterialEvent);
    }
}
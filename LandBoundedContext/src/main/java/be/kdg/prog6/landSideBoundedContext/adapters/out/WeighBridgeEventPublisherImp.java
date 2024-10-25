


package be.kdg.prog6.landSideBoundedContext.adapters.out;

import be.kdg.prog6.landSideBoundedContext.domain.WeighInEvent;
import be.kdg.prog6.landSideBoundedContext.domain.WeighOutEvent;
import be.kdg.prog6.landSideBoundedContext.port.out.WeighBridgeEventPublisher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.stereotype.Component;

@Component
public class WeighBridgeEventPublisherImp implements WeighBridgeEventPublisher {

    private static final Logger logger = LogManager.getLogger(WeighBridgeEventPublisherImp.class);
    private final RabbitTemplate rabbitTemplate;

    public WeighBridgeEventPublisherImp(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private static final String EXCHANGE_NAME = "weighbridgeExchange";


    @Override
    public void publishTruckWeightedIn(WeighInEvent weighInEvent) {
        final String routingKey = "truck." + weighInEvent.getLicensePlate() + ".in";

        logger.info("Notifying RabbitMQ: Truck in  {}", routingKey);

        rabbitTemplate.convertAndSend(EXCHANGE_NAME, routingKey, weighInEvent);
    }

    @Override
    public void publishTruckWeighedOut(WeighOutEvent weighOutEvent) {
        final String routingKey = "truck." + weighOutEvent.getLicensePlate() + ".out";
        final String exchangeName = "weighbridgeExchange";

        logger.info("Notifying RabbitMQ: Truck Out {}", routingKey);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, weighOutEvent);

    }
}
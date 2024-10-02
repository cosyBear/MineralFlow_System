package be.kdg.prog6.LandSideBoundedContext.adapters.out;

import be.kdg.prog6.LandSideBoundedContext.domain.WeighInEvent;
import be.kdg.prog6.LandSideBoundedContext.port.out.WeighBridgeEventPublisher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.stereotype.Component;

@Component
public class WeighBridgeEventPublisherImp implements WeighBridgeEventPublisher {
    private final RabbitTemplate rabbitTemplate;

    private static final Logger logger = LogManager.getLogger(WeighBridgeEventPublisherImp.class);

    public WeighBridgeEventPublisherImp(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publishTruckWeightedIn(WeighInEvent event) {
        final String routingKey = "truck." + event.getLicensePlate() + ".in";
        final String exchangeName = "weighbridgeExchange";
        logger.info("Publishing truck weighbridge: " + event.getLicensePlate());
        logger.info("The Truck has been weight: " + event.getLicensePlate());
        rabbitTemplate.convertAndSend(exchangeName, routingKey, event);
    }




    @Override
    public void publishTruckWeighedOut(WeighInEvent event) {
        final String routingKey = "truck." + event.getLicensePlate() + ".out";
        final String exchangeName = "weighbridgeExchange";
        rabbitTemplate.convertAndSend(exchangeName, routingKey, event);

    }
}
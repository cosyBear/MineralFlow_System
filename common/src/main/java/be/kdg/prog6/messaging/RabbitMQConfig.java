package be.kdg.prog6.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String WEIGHBRIDGE_EXCHANGE = "weighbridgeExchange";
    public static final String TRUCK_WEIGHTED_IN_QUEUE = "truckWeightedInQueue";

    @Bean
    public TopicExchange weighbridgeExchange() {
        return new TopicExchange(WEIGHBRIDGE_EXCHANGE);
    }

    @Bean
   public Queue truckWeightedInQueue() {
        return new Queue(TRUCK_WEIGHTED_IN_QUEUE, true);
    }

    @Bean
    public Binding truckWeightedInBinding(Queue truckWeightedInQueue, TopicExchange weighbridgeExchange) {
        return BindingBuilder.bind(truckWeightedInQueue)
                .to(weighbridgeExchange).
                with("truck.*.in");
    }

    @Bean
    RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

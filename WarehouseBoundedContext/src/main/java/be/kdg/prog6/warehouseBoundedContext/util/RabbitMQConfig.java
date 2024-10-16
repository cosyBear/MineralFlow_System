package be.kdg.prog6.warehouseBoundedContext.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String WAREHOUSE_EXCHANGE = "WarehouseExchange";
    public static final String WAREHOUSE_MATERIAL_QUEUE = "WarehouseMaterial_QUEUE";
    public static final String ROUTING_KEY = "WarehouseRoutingKey";

    // Create the direct exchange
    @Bean
    public DirectExchange warehouseExchange() {
        return new DirectExchange(WAREHOUSE_EXCHANGE);
    }

    // Create the queue
    @Bean
    public Queue warehouseMaterialQueue() {
        return new Queue(WAREHOUSE_MATERIAL_QUEUE, true); // 'true' means the queue will be durable
    }

    // Bind the queue to the exchange with a routing key
    @Bean
    public Binding warehouseBinding(Queue warehouseMaterialQueue, DirectExchange warehouseExchange) {
        return BindingBuilder.bind(warehouseMaterialQueue)
                .to(warehouseExchange)
                .with(ROUTING_KEY);
    }
    @Bean
    RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());  // Register the module to handle Java 8 Date/Time API
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);  // Optional: disable timestamps

        return new Jackson2JsonMessageConverter(objectMapper);
    }

}

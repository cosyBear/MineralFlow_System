package be.kdg.prog6.LandSideBoundedContext.util.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
    public static final String TRUCK_WEIGHTED_OUT_QUEUE = "truckWeightedOutQueue";

    @Bean
    public TopicExchange weighbridgeExchange() {
        return new TopicExchange(WEIGHBRIDGE_EXCHANGE);
    }

    @Bean
   public Queue truckWeightedInQueue() {
        return new Queue(TRUCK_WEIGHTED_IN_QUEUE, true);
    }


    @Bean
    public Queue truckWeightedOutQueue() {
        return new Queue(TRUCK_WEIGHTED_OUT_QUEUE, true);
    }


    @Bean
    public Binding truckWeightedInBinding(Queue truckWeightedInQueue, TopicExchange weighbridgeExchange) {
        return BindingBuilder.bind(truckWeightedInQueue)
                .to(weighbridgeExchange).
                with("truck.*.in");
    }

    @Bean
    public Binding truckWeightedOutBinding(Queue truckWeightedOutQueue, TopicExchange weighbridgeExchange) {
        return BindingBuilder.bind(truckWeightedOutQueue).to(weighbridgeExchange).with("truck.*.out");
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Register the JavaTimeModule
        return new Jackson2JsonMessageConverter(objectMapper);
    }



}

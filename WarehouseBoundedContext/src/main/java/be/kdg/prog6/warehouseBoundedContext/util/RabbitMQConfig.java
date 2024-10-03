package be.kdg.prog6.warehouseBoundedContext.util;

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

    public static final String PDT_EXCHANGE = "PdtExchange";
    public static final String PDT_Created_QUEUE = "PDT_CREATED_QUEUE";

    @Bean
    public TopicExchange PDTExchange() {
        return new TopicExchange(PDT_EXCHANGE);
    }

    @Bean
   public Queue PDTCREATEDQueue() {
        return new Queue(PDT_Created_QUEUE, true);
    }

    @Bean
    public Binding ptdCreatedEventBinding(Queue PDTCREATEDQueue, TopicExchange PDTExchange) {
        return BindingBuilder.bind(PDTCREATEDQueue)
                .to(PDTExchange).
                with("warehouse.pdt.generated");
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

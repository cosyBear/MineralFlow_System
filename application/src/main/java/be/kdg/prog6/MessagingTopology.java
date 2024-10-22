package be.kdg.prog6;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
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
public class MessagingTopology {


    public static final String WAREHOUSE_EXCHANGE = "WarehouseExchange";
    public static final String WAREHOUSE_MATERIAL_QUEUE = "WarehouseMaterial_QUEUE";
    public static final String ROUTING_KEY = "WarehouseRoutingKey";
    private static final String RABBITMQ_USERNAME = "myuser"; // replace with your username
    private static final String RABBITMQ_PASSWORD = "mypassword"; // replace with your password
    private static final String RABBITMQ_VIRTUAL_HOST = "/"; // if you have a specific virtual host
    private static final String RABBITMQ_HOST = "localhost"; // or your RabbitMQ server's host

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(RABBITMQ_HOST);
        connectionFactory.setUsername(RABBITMQ_USERNAME);
        connectionFactory.setPassword(RABBITMQ_PASSWORD);
        connectionFactory.setVirtualHost(RABBITMQ_VIRTUAL_HOST);
        return connectionFactory;
    }
    // Create the direct exchange
    @Bean
    public DirectExchange warehouseExchange() {
        return new DirectExchange(WAREHOUSE_EXCHANGE);
    }

    // Create the queue
    @Bean
    public Queue warehouseMaterialQueue() {
        return new Queue(WAREHOUSE_MATERIAL_QUEUE); // 'true' means the queue will be durable
    }

    // Bind the queue to the exchange with a routing key
    @Bean
    public Binding warehouseBinding(Queue warehouseMaterialQueue, DirectExchange warehouseExchange) {
        return BindingBuilder.bind(warehouseMaterialQueue)
                .to(warehouseExchange)
                .with(ROUTING_KEY);
    }

        @Bean
        public MessageConverter jsonMessageConverter() {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            return new Jackson2JsonMessageConverter(objectMapper);
        }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jsonMessageConverter());
        return factory;
    }


    public static final String WEIGHBRIDGE_EXCHANGE = "weighbridgeExchange";
    public static final String TRUCK_WEIGHTED_IN_QUEUE = "truckWeightedInQueue";
    public static final String TRUCK_WEIGHTED_Out_QUEUE = "truckWeightedOutQueue";

    @Bean
    public TopicExchange weighbridgeExchange() {
        return new TopicExchange(WEIGHBRIDGE_EXCHANGE);
    }

    @Bean
    Queue truckWeightedInQueue() {
        return new Queue(TRUCK_WEIGHTED_IN_QUEUE);
    }

    @Bean
    Queue truckWeightedOutQueue() {
        return new Queue(TRUCK_WEIGHTED_Out_QUEUE);
    }


    @Bean
    Binding truckWeightedInBinding(Queue truckWeightedInQueue, TopicExchange weighbridgeExchange) {
        return BindingBuilder.bind(truckWeightedInQueue)
                .to(weighbridgeExchange).
                with("truck.*.in");
    }



    @Bean
    public Binding truckWeightedOutBinding(Queue truckWeightedOutQueue, TopicExchange weighbridgeExchange) {
        return BindingBuilder.bind(truckWeightedOutQueue)
                .to(weighbridgeExchange).
                with("truck.*.out");
    }


    // water side config


    // Water side exchange and queue names
    private static final String WATER_SIDE_EXCHANGE = "waterSideExchange";
    private static final String SHIP_IN_QUEUE = "shipInQueue";
    private static final String SHIP_OUT_QUEUE = "shipOutQueue";

    @Bean
    public TopicExchange waterSideExchange() {
        return new TopicExchange(WATER_SIDE_EXCHANGE);
    }

    @Bean
    public Queue shipInQueue() {
        return new Queue(SHIP_IN_QUEUE, true);
    }

    @Bean
    public Queue shipOutQueue() {
        return new Queue(SHIP_OUT_QUEUE, true);
    }

    @Bean
    public Binding shipInBinding(Queue shipInQueue, TopicExchange waterSideExchange) {
        return BindingBuilder.bind(shipInQueue)
                .to(waterSideExchange)
                .with("ship.#.in");
    }

    @Bean
    public Binding shipOutBinding(Queue shipOutQueue, TopicExchange waterSideExchange) {
        return BindingBuilder.bind(shipOutQueue)
                .to(waterSideExchange)
                .with("ship.#.out");
    }





}

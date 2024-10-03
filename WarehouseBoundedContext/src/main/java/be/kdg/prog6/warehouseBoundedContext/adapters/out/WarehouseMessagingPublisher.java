//package be.kdg.prog6.boundedcontextB.adapters.out;
//
//import be.kdg.prog6.boundedcontextB.domain.Warehouse;
//import be.kdg.prog6.boundedcontextB.port.out.EventPublisherPort;
//import be.kdg.prog6.boundedcontextB.port.out.UpdateWarehousePort;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class WarehouseMessagingPublisher implements EventPublisherPort {
//
//    private final RabbitTemplate rabbitTemplate;
//
//    public WarehouseMessagingPublisher(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }
//
//
//    public void publishPDTCompletedEvent(PDT pdt) {
//        // you would call this when the truck meaning when its back at the land side
//        // the way we do it now is when i want the truck to leave is using api call from the land side os i guess i can trigger event
//        PDTCompletedEvent event = new PDTCompletedEvent(
//                pdt.getMaterialType(),
//                pdt.getDeliveryTime(),
//                pdt.getPayload(),
//                pdt.getNetWeight(),
//                pdt.getWarehouseNumber()
//        );
//
//        rabbitTemplate.convertAndSend("pdtExchange", "pdt.completed", event);
//    }
//}
package be.kdg.prog6.warehouseBoundedContext.adapters.out;

import be.kdg.prog6.warehouseBoundedContext.domain.Warehouse;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseEvent;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseMaterialEvent;
import be.kdg.prog6.warehouseBoundedContext.port.out.EventPublisherPort;
import be.kdg.prog6.warehouseBoundedContext.port.out.Warehouse.WarehouseSavePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
@Service
public class WarehouseMessagingPublisher implements WarehouseSavePort {
    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseMessagingPublisher.class);
    private final RabbitTemplate rabbitTemplate;
    public WarehouseMessagingPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void save(Warehouse warehouse, WarehouseEvent event) {
        LOGGER.info("notifying  the landSide about the material amount in the warehouse");
        LOGGER.info("event object attrubties: amountOfMat{} , ");

        WarehouseMaterialEvent warehouseEvent = new WarehouseMaterialEvent(
                warehouse.getWarehouseNumber().getId(),
                event.materialTrueWeight(),
                event.getMaterialType(),
                warehouse.getSellerId().getSellerID()
        );
        rabbitTemplate.convertAndSend("WarehouseExchange", "WarehouseRoutingKey", warehouseEvent);

    }
}
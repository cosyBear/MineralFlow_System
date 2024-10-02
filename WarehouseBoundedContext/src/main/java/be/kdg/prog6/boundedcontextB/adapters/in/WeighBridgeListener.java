//package be.kdg.prog6.boundedcontextB.adapters.in;
//import be.kdg.prog6.boundedcontextB.domain.weighTruckInCommand;
//import be.kdg.prog6.boundedcontextB.dto.weighTruckInDto;
//import be.kdg.prog6.boundedcontextB.port.in.WarehouseUseCase;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class WeighBridgeListener {
//    private static final Logger LOGGER = LoggerFactory.getLogger(WeighBridgeListener.class);
//
//    private static final String truckWeightedInQueue = "truckWeightedInQueue";
//    private static final String truckWeightedOutQueue = "truckWeightedOutQueue";
//    private final WarehouseUseCase wareHouseUseCase;
//
//    public WeighBridgeListener(WarehouseUseCase warehouseUseCase) {
//        this.wareHouseUseCase = warehouseUseCase;
//    }
//
//
//    @RabbitListener(queues = truckWeightedInQueue)
//    public void truckWeightedIn(weighTruckInDto dto ) {
//
//        weighTruckInCommand command = new weighTruckInCommand( dto.weighBridgeTicketId() , dto.licensePlate(), dto.sellerId(), dto.grossWeight(), dto.materialType() , dto.weighInTime());
//
//        wareHouseUseCase.TruckIn(command);
//
//    }
//
//
//}

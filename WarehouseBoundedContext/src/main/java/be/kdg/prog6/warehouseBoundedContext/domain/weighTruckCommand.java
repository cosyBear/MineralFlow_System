package be.kdg.prog6.warehouseBoundedContext.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public record weighTruckCommand(UUID weighBridgeTicketId, String licensePlate , SellerId sellerId , double grossWeight, MaterialType materialType,
                                LocalDateTime weighInTime , String wareHouseStatus) {

}

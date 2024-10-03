package be.kdg.prog6.warehouseBoundedContext.domain;

import java.time.LocalDateTime;
import java.util.UUID;
public record  weighTruckOutCommand (UUID weighBridgeTicketId, String licensePlate , SellerId sellerId , double materialTrueWeight, MaterialType materialType,
                                    LocalDateTime weighInTime ) {

}
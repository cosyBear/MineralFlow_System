package be.kdg.prog6.warehouseBoundedContext.util.Mapper;


import be.kdg.prog6.warehouseBoundedContext.domain.*;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UuidMapper {

    @Named("warehouseIdToUuid")
    default UUID warehouseIdToUuid(WarehouseId warehouseId) {
        return warehouseId != null ? warehouseId.id() : null;
    }

    @Named("uuidToWarehouseId")
    default WarehouseId uuidToWarehouseId(UUID uuid) {
        return uuid != null ? new WarehouseId(uuid) : null;
    }

    @Named("warehouseEventIdToUuid")
    default UUID warehouseEventIdToUuid(WarehouseEventId warehouseEventId) {
        return warehouseEventId != null ? warehouseEventId.id() : null;
    }

    @Named("uuidToWarehouseEventId")
    default WarehouseEventId uuidToWarehouseEventId(UUID uuid) {
        return uuid != null ? new WarehouseEventId(uuid) : null;
    }

    @Named("sellerIdToUuid")
    default UUID sellerIdToUuid(SellerId sellerId) {
        return sellerId != null ? sellerId.getSellerID() : null;
    }

    @Named("uuidToSellerId")
    default SellerId uuidToSellerId(UUID uuid) {
        return uuid != null ? new SellerId(uuid) : null;
    }
}


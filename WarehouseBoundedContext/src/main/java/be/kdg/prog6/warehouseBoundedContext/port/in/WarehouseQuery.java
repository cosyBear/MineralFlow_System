package be.kdg.prog6.warehouseBoundedContext.port.in;

import be.kdg.prog6.warehouseBoundedContext.domain.SellerId;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseId;

import java.util.List;
import java.util.UUID;

public record WarehouseQuery(  UUID warehouseId,
                               UUID sellerId,
                               String materialType,
                               List<WarehouseEventQuery> warehouseEvents) {



}



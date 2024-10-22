package be.kdg.prog6.warehouseBoundedContext.adapters.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record PurchaseOrderDto(LocalDate orderDate,
                               String sellerId ,
                               String customerName,
                               UUID buyerId,
                               List<OrderLineDto> orderLines) {
}

package be.kdg.prog6.warehouseBoundedContext.adapters.dto;

import java.time.LocalDate;
import java.util.List;

public record PurchaseOrderDto(LocalDate orderDate,
                               String sellerId ,
                               String customerName,
                               List<OrderLineDto> orderLines) {
}

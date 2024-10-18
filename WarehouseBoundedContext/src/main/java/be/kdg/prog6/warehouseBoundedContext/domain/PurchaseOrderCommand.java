package be.kdg.prog6.warehouseBoundedContext.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


public record PurchaseOrderCommand(LocalDate orderDate, UUID sellerId , String customerName,
    List<OrderLineCommand> orderlineList) {
}

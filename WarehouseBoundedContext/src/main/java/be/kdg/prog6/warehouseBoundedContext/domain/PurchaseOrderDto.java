package be.kdg.prog6.warehouseBoundedContext.domain;

import java.time.LocalDate;

public record PurchaseOrderDto(LocalDate orderDate, String sellerId , String customerName
, String  materialType , double amountOfMaterialInTons) {
}

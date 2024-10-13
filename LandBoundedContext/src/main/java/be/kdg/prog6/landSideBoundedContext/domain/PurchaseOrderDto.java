package be.kdg.prog6.landSideBoundedContext.domain;

import java.time.LocalDate;

public record PurchaseOrderDto(LocalDate orderDate, String sellerId , String customerName
, String  materialType , double amountOfMaterialInTons) {
}

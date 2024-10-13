package be.kdg.prog6.landSideBoundedContext.domain;

import java.time.LocalDate;
import java.util.UUID;


public record PurchaseOrderCommand(LocalDate orderDate, UUID sellerId , String customerName
        , MaterialType materialType , double amountOfMaterialInTons) {
}

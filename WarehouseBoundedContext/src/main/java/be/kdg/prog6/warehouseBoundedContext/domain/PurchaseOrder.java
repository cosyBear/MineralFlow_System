package be.kdg.prog6.warehouseBoundedContext.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import be.kdg.prog6.warehouseBoundedContext.util.Error.InspectionOperationException;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class PurchaseOrder {
    // the date is when the order was placed.
    private LocalDate orderDate;

    private UUID purchaseOrderId;

    private SellerId sellerId;

    private String CustomerName;

    private List<PurchaseOrderLine> orderLines = new ArrayList<>();  // List of materials and quantities being purchased

    private PurchaseOrderStatus status ;

    public PurchaseOrder(LocalDate orderDate, UUID purchaseOrderId, SellerId sellerId, String customerName, List<PurchaseOrderLine> orderLines , PurchaseOrderStatus status) {
        this.orderDate = orderDate;
        this.purchaseOrderId = purchaseOrderId;
        this.sellerId = sellerId;
        this.CustomerName = customerName;
        this.orderLines = orderLines;
        this.status = status;
    }

    public PurchaseOrder(LocalDate orderDate, SellerId sellerId, String customerName , PurchaseOrderStatus status) {
        this.orderDate = orderDate;
        this.sellerId = sellerId;
        CustomerName = customerName;
        this.status = status;
    }




}

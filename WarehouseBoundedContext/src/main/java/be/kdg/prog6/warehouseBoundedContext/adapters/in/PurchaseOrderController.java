package be.kdg.prog6.warehouseBoundedContext.adapters.in;


import be.kdg.prog6.warehouseBoundedContext.adapters.dto.PurchaseOrderDto;
import be.kdg.prog6.warehouseBoundedContext.port.in.OrderLineCommand;
import be.kdg.prog6.warehouseBoundedContext.domain.PurchaseOrder;
import be.kdg.prog6.warehouseBoundedContext.port.in.PurchaseOrderCommand;
import be.kdg.prog6.warehouseBoundedContext.port.in.PurchaseOrderUseCase;
import domain.MaterialType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("PurchaseOrders")
public class PurchaseOrderController {


    private final PurchaseOrderUseCase purchaseOrderUseCase;

    public PurchaseOrderController(PurchaseOrderUseCase purchaseOrderUseCase) {
        this.purchaseOrderUseCase = purchaseOrderUseCase;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('Manager')")
    public ResponseEntity<String> createPurchaseOrder(@RequestBody PurchaseOrderDto dto) {

        List<OrderLineCommand> orderLineDtos = dto.orderLines().stream().map(
                item -> {
                    return new OrderLineCommand(MaterialType.valueOf(item.materialType()), item.quantity(), item.pricePerTon());
                }
        ).toList();

        PurchaseOrderCommand command = new PurchaseOrderCommand(dto.orderDate(), UUID.fromString(dto.sellerId()),
                dto.customerName(), dto.buyerId(), orderLineDtos);
        purchaseOrderUseCase.createPurchaseOrder(command);
        return ResponseEntity.status(HttpStatus.CREATED).body("the order has be created");

    }

    @GetMapping
    public ResponseEntity<List<PurchaseOrder>> getPurchaseOrdersStatus() {
        List<PurchaseOrder> purchaseOrderList = purchaseOrderUseCase.getPurchaseOrderStatus();
        return ResponseEntity.ok(purchaseOrderList);
    }

}


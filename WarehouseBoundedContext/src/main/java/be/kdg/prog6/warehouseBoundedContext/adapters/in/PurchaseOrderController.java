package be.kdg.prog6.warehouseBoundedContext.adapters.in;


import be.kdg.prog6.warehouseBoundedContext.adapters.dto.OrderLineDto;
import be.kdg.prog6.warehouseBoundedContext.adapters.dto.PurchaseOrderDto;
import be.kdg.prog6.warehouseBoundedContext.domain.OrderLineCommand;
import be.kdg.prog6.warehouseBoundedContext.domain.PurchaseOrder;
import be.kdg.prog6.warehouseBoundedContext.domain.PurchaseOrderCommand;
import be.kdg.prog6.warehouseBoundedContext.port.in.PurchaseOrderUseCase;
import domain.MaterialType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("PurchaseOrders")
public class PurchaseOrderController {


    private final PurchaseOrderUseCase purchaseOrderUseCase;

    public PurchaseOrderController(PurchaseOrderUseCase purchaseOrderUseCase) {
        this.purchaseOrderUseCase = purchaseOrderUseCase;
    }

    @PostMapping
    public ResponseEntity<String> createPurchaseOrder(@RequestBody PurchaseOrderDto dto) {

        List<OrderLineCommand> orderLineDtos = dto.orderLines().stream().map(
                item -> {
                    return new OrderLineCommand(MaterialType.valueOf(item.materialType()), item.quantity(), item.pricePerTon());
                }
        ).toList();

        PurchaseOrderCommand command = new PurchaseOrderCommand(dto.orderDate(), UUID.fromString(dto.sellerId()),
                dto.customerName(), orderLineDtos);
        purchaseOrderUseCase.createPurchaseOrder(command);
        return ResponseEntity.ok("the order has be created");

    }
    @GetMapping()
    public ResponseEntity<List<PurchaseOrder>> getPurchaseOrdersStatus(){
       List<PurchaseOrder> purchaseOrderList  = purchaseOrderUseCase.getPurchaseOrderStatus();
        return ResponseEntity.ok(purchaseOrderList);
    }

}


package be.kdg.prog6.landSideBoundedContext.adapters.in;


import be.kdg.prog6.landSideBoundedContext.domain.PurchaseOrderCommand;
import be.kdg.prog6.landSideBoundedContext.domain.PurchaseOrderDto;
import be.kdg.prog6.landSideBoundedContext.port.in.PurchaseOrderUseCase;
import domain.MaterialType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("PurchaseOrder")
public class PurchaseOrderController {


    private final PurchaseOrderUseCase purchaseOrderUseCase;

    public PurchaseOrderController(PurchaseOrderUseCase purchaseOrderUseCase) {
        this.purchaseOrderUseCase = purchaseOrderUseCase;
    }

    @PostMapping
    public ResponseEntity<String> createPurchaseOrder(@RequestBody PurchaseOrderDto dto) {

        PurchaseOrderCommand command = new PurchaseOrderCommand(dto.orderDate() , UUID.fromString(dto.sellerId()), dto.customerName(), MaterialType.valueOf(dto.materialType()), dto.amountOfMaterialInTons() );
        purchaseOrderUseCase.CreatePurchaseOrder(command);
        return ResponseEntity.ok("the order has be created");

    }


}

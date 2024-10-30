package be.kdg.prog6.watersideboundedcontext.adapters.in;


import be.kdg.prog6.watersideboundedcontext.port.in.ShipmentOrderUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.kdg.prog6.watersideboundedcontext.adapters.dto.ShipArrivalRequestDto;
import be.kdg.prog6.watersideboundedcontext.port.in.RequestMaterialCommand;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/ships")
public class ShipArrivalController {



    private final ShipmentOrderUseCase useCase;

    public ShipArrivalController(ShipmentOrderUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('Seller')")

    public ResponseEntity<String> shipRequestDelivery(@RequestBody ShipArrivalRequestDto dto){
        RequestMaterialCommand shipIn = new RequestMaterialCommand(UUID.fromString(dto.purchaseOrder()), UUID.fromString(dto.shipmentOrder()) , UUID.fromString(dto.vesselNumber()) , LocalDateTime.parse(dto.arrivalTime()));
        useCase.requestMaterial(shipIn);
        return ResponseEntity.status(HttpStatus.CREATED).body("we received the ShipArrivalRequest  everything is ok...");

    }





}

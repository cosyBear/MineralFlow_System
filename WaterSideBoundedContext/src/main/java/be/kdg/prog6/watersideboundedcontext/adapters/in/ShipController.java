package be.kdg.prog6.watersideboundedcontext.adapters.in;


import be.kdg.prog6.watersideboundedcontext.port.in.ShipmentOrderUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.kdg.prog6.watersideboundedcontext.adapters.dto.ShipOrderDto;
import be.kdg.prog6.watersideboundedcontext.port.in.ShipmentOrderCommand;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/ships")
public class ShipController {



    private final ShipmentOrderUseCase useCase;

    public ShipController(ShipmentOrderUseCase useCase) {
        this.useCase = useCase;
    }


    @PostMapping
    public ResponseEntity<String> shipRequestDelivery(@RequestBody  ShipOrderDto dto){
        ShipmentOrderCommand shipIn = new ShipmentOrderCommand(UUID.fromString(dto.purchaseOrder()), UUID.fromString(dto.shipmentOrder()) , UUID.fromString(dto.vesselNumber()) , LocalDateTime.parse(dto.arrivalTime()));

        useCase.requestMaterial(shipIn);
        return ResponseEntity.ok("everything is ok ");

    }





}

package be.kdg.prog6.watersideboundedcontext.adapters.in;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.kdg.prog6.watersideboundedcontext.adapters.dto.ShipOrderDto;
import be.kdg.prog6.watersideboundedcontext.domain.ShipmentOrderCommand;
import be.kdg.prog6.watersideboundedcontext.domain.Vessel;

@RestController
@RequestMapping("/ships")
public class ShipController {



    @PostMapping
    public ResponseEntity<String> shipRequestDelivery(ShipOrderDto dto){
        // not to sure what i need to return here.
        // what is the (IO) and the (BO)
        ShipmentOrderCommand shipIn = new ShipmentOrderCommand(dto.purchaseOrder(),new Vessel(dto.vesselNumber()), dto.arrivalTime());

        return ResponseEntity.ok("everything is ok ");

    }





}

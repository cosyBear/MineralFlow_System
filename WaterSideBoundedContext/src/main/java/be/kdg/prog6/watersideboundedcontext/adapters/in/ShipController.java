package be.kdg.prog6.watersideboundedcontext.adapters.in;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ships")
public class ShipController {






        @PostMapping
    public ResponseEntity<String> shipRequestDelivery(){



        return ResponseEntity.ok("everything is ok ");

    }





}

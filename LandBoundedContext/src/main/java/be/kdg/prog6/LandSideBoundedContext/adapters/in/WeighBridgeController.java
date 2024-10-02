package be.kdg.prog6.LandSideBoundedContext.adapters.in;


import be.kdg.prog6.LandSideBoundedContext.dto.WeighInDto;
import be.kdg.prog6.LandSideBoundedContext.port.in.WeighBridgeUseCase;
import be.kdg.prog6.LandSideBoundedContext.port.in.weighTruckInCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Bridge")
public class WeighBridgeController {


    private final WeighBridgeUseCase weighBridgeUseCase;

    public WeighBridgeController(WeighBridgeUseCase weighBridgeUseCase) {
        this.weighBridgeUseCase = weighBridgeUseCase;
    }


    @PostMapping("/weigh-in")
    public ResponseEntity<Void> weighTruckIn(@RequestBody WeighInDto event) {

            weighTruckInCommand command =  new weighTruckInCommand(event.licensePlate() , event.startWeight() , event.materialType() , event.sellerId() , event.weighInTime());
            weighBridgeUseCase.weighTruckIn(command);

            return ResponseEntity.ok().build();
    }

    //TODO clean your code and use the correct name for the method and the attrubites and the like for the Postmapping and the RequestMapping
    // jsut make better names

    @PostMapping("/weigh-out")
    // one question to the teacher for the EndWeight is it always the same or its random or is there some rules
    public ResponseEntity<Void> weighTruckOut(@RequestBody WeighInDto event) {
        //todo SO WHAT THIS CONTROLLER WILL DO IS will have  A DIFFERENT attrubties the double EndWeight is the new attributes
        // this is just a fake values you give for the truck Weight by it self with out the MMaterials.
        // like you say in the (weighTruckIn) Method you send the (double startWeight) let say its  50tons for Example
        // now you say ok in the when the truck leaves  calling the (weighTruckOut) using a API call  the double EndWeight is like 4tons this is a fake value you can say it.
        // then you would go to the landslide you call a method to get the WBT ticket based on the weighBridgeTicketId and correct the it or aka just adding the attrubties EndWeigth to it.
        // and may like add extra Attribute like call it the material i deliver this is just the material without the truck NOTEEEEEEEEe this line is (optional)
        // now you have all the info for the truck Weight and the Material Weight now you create an other event of the WeighInEvent with the material Weight ONLY aka the endWeight publish that event to the warehouse and than there correct the Event in the warehouse you gave a value of 0 for the Weight  and you are done
        // very important read the doc of the porject beusee there is some stuff you need to fix that i missed like read the user story and go over your code and read the lanside and go over your code and you are done .
        return ResponseEntity.ok().build();
    }

}

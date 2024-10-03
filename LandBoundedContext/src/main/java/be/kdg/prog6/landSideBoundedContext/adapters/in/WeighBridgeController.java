package be.kdg.prog6.landSideBoundedContext.adapters.in;


import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import be.kdg.prog6.landSideBoundedContext.domain.LicensePlate;
import be.kdg.prog6.landSideBoundedContext.dto.WeighInDto;
import be.kdg.prog6.landSideBoundedContext.dto.WeighOutDto;
import be.kdg.prog6.landSideBoundedContext.port.in.WeighBridgeUseCase;
import be.kdg.prog6.landSideBoundedContext.port.in.weighTruckInCommand;
import be.kdg.prog6.landSideBoundedContext.port.in.weighTruckOutCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/bridge")
public class WeighBridgeController {


    private final WeighBridgeUseCase weighBridgeUseCase;

    public WeighBridgeController(WeighBridgeUseCase weighBridgeUseCase) {
        this.weighBridgeUseCase = weighBridgeUseCase;
    }


    @PostMapping("/weigh-in")
    public ResponseEntity<Void> weighTruckIn(@RequestBody WeighInDto dto) {

            weighTruckInCommand TruckInCommand =  new weighTruckInCommand(new LicensePlate(dto.licensePlate() ), dto.startWeight() , dto.materialType() , new SellerId(UUID.fromString(dto.sellerId())), LocalDateTime.parse(dto.weighInTime()));
            weighBridgeUseCase.weighTruckIn(TruckInCommand);
            return ResponseEntity.ok().build();
    }



    @PostMapping("/weigh-out")
    public ResponseEntity<Void> weighTruckOut(@RequestBody WeighOutDto dto) {

        weighTruckOutCommand  truckOutCommand = new weighTruckOutCommand(
                dto.licensePlate(), dto.endWeight() , dto.materialType() , dto.sellerId() , dto.weighInTime()
        );
        weighBridgeUseCase.weighTruckOut(truckOutCommand);

        return ResponseEntity.ok().build();
    }

}

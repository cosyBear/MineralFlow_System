package be.kdg.prog6.landSideBoundedContext.adapters.in;


import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import be.kdg.prog6.landSideBoundedContext.domain.Id.WeighBridgeTicketId;
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
@RequestMapping("/weighbridge")
public class WeighBridgeController {


    private final WeighBridgeUseCase weighBridgeUseCase;

    public WeighBridgeController(WeighBridgeUseCase weighBridgeUseCase) {
        this.weighBridgeUseCase = weighBridgeUseCase;
    }


    @PostMapping("/trucks/weighIn")
    public ResponseEntity<Void> weighTruckIn(@RequestBody WeighInDto dto) {
        weighTruckInCommand TruckInCommand = new weighTruckInCommand(new LicensePlate(dto.licensePlate()), dto.startWeight(), dto.materialType(), new SellerId(UUID.fromString(dto.sellerId())), LocalDateTime.parse(dto.weighInTime()));
        weighBridgeUseCase.weighTruckIn(TruckInCommand);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/trucks/weighOut")
    public ResponseEntity<Void> weighTruckOut(@RequestBody WeighOutDto dto) {

        weighTruckOutCommand truckOutCommand = new weighTruckOutCommand(
                dto.licensePlate(), dto.endWeight(), dto.materialType(), new SellerId(UUID.fromString(dto.sellerId())), dto.weighInTime() , dto.warehouseStatus() , new WeighBridgeTicketId(UUID.fromString(dto.WeighBridgeTicketId()))
        );

        weighBridgeUseCase.weighTruckOut(truckOutCommand);

        return ResponseEntity.ok().build();
    }

}

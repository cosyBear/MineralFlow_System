package be.kdg.prog6.landSideBoundedContext.adapters.in;


import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import be.kdg.prog6.landSideBoundedContext.domain.Id.WeighBridgeTicketId;
import be.kdg.prog6.landSideBoundedContext.domain.LicensePlate;
import be.kdg.prog6.landSideBoundedContext.adapters.in.dto.WeighInDto;
import be.kdg.prog6.landSideBoundedContext.adapters.in.dto.WeighOutDto;
import be.kdg.prog6.landSideBoundedContext.port.in.WeighBridgeUseCase;
import be.kdg.prog6.landSideBoundedContext.port.in.WeighTruckInCommand;
import be.kdg.prog6.landSideBoundedContext.port.in.weighTruckOutCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('basicUser')")
    public ResponseEntity<String> weighTruckIn(@RequestBody WeighInDto dto) {
        WeighTruckInCommand truckInCommand = new WeighTruckInCommand(new LicensePlate(dto.licensePlate()), dto.startWeight(), dto.materialType(), new SellerId(UUID.fromString(dto.sellerId())), LocalDateTime.parse(dto.weighInTime()));
        String message = weighBridgeUseCase.weighTruckIn(truckInCommand);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PreAuthorize("hasAuthority('basicUser')")
    @PostMapping("/trucks/weighOut")
    public ResponseEntity<String> weighTruckOut(@RequestBody WeighOutDto dto) {
        weighTruckOutCommand truckOutCommand = new weighTruckOutCommand(
                dto.licensePlate(), dto.endWeight(), dto.materialType(), new SellerId(UUID.fromString(dto.sellerId())), dto.weighOutTime(), new WeighBridgeTicketId(UUID.fromString(dto.WeighBridgeTicketId())));
        String message = weighBridgeUseCase.weighTruckOut(truckOutCommand);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

}

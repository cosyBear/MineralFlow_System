package be.kdg.prog6.boundedcontextB.adapters.in;


import be.kdg.prog6.boundedcontextB.core.BridgeCommand;
import be.kdg.prog6.boundedcontextB.domain.LicensePlate;
import be.kdg.prog6.boundedcontextB.domain.MaterialType;
import be.kdg.prog6.boundedcontextB.domain.Truck;
import be.kdg.prog6.boundedcontextB.dto.BridgeDto;
import be.kdg.prog6.boundedcontextB.port.in.weighingBridgeUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("load")
public class weighingBridgeController {
    //TODO change the name of the controller. have no idea what the name should be just texting and trying to understand

    private weighingBridgeUseCase weighingBridgeUseCase;


    public weighingBridgeController(weighingBridgeUseCase weighingBridgeUseCase) {
        this.weighingBridgeUseCase = weighingBridgeUseCase;
    }


    @PostMapping
    public ResponseEntity<String> HandleTruck(BridgeDto bridgeDto) {

//      BridgeCommand bridgeCommand = new BridgeCommand(new LicensePlate(bridgeDto.licensePlate()) , MaterialType.valueOf(bridgeDto.materialType()));
        Truck truck  = new Truck(new LicensePlate(bridgeDto.licensePlate()) , MaterialType.valueOf(bridgeDto.materialType()) , bridgeDto.payLoad());

        weighingBridgeUseCase.processTruck(truck);

        return ResponseEntity.ok(" \n something Happend . \n");
    }




}

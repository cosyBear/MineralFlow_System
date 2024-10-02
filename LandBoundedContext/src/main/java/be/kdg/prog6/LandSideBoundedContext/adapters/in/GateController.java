package be.kdg.prog6.LandSideBoundedContext.adapters.in;

import be.kdg.prog6.LandSideBoundedContext.domain.Appointment;
import be.kdg.prog6.LandSideBoundedContext.dto.GateDto;
import be.kdg.prog6.LandSideBoundedContext.port.in.GateCommand;
import be.kdg.prog6.LandSideBoundedContext.port.in.GateUseCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gate")
public class GateController {

    private static final Logger logger = LogManager.getLogger(GateController.class);
    private final GateUseCase gateUseCase;


    public GateController(GateUseCase gateUseCase) {
        this.gateUseCase = gateUseCase;
    }

    @PostMapping
    public ResponseEntity<Appointment> askForGateToOpen(@RequestBody GateDto gateDto){

       return ResponseEntity.ok(gateUseCase.GateSecurity(new GateCommand(gateDto.licensePlate() , gateDto.localDate())));

    }


}

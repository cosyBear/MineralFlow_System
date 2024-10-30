package be.kdg.prog6.watersideboundedcontext.adapters.in;

import be.kdg.prog6.watersideboundedcontext.adapters.dto.OperationRequestDto;
import be.kdg.prog6.watersideboundedcontext.port.in.OperationRequestCommand;
import be.kdg.prog6.watersideboundedcontext.port.in.ShipmentOperationsPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("operations")
public class OperationsController {




    private final ShipmentOperationsPort operationsPort;

    public OperationsController(ShipmentOperationsPort operationsPort) {
        this.operationsPort = operationsPort;
    }


    @PostMapping("inspection-bunkering-process")
//    @PreAuthorize("hasAuthority('Seller')")
    public ResponseEntity<String> performInspectionAndBunkering(@RequestBody OperationRequestDto dto) {
        OperationRequestCommand operationRequestCommand = new OperationRequestCommand(UUID.fromString(dto.shipmentOrderId()), dto.inspectionTimeOfSigning(), dto.inspectorSignature(), dto.bunkeringTime());

        if (operationsPort.performBunkeringOperationAndInspectionOperation(operationRequestCommand)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Inspection and bunkering operations completed successfully ");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Inspection and bunkering operations are  Not successful ");
        }
    }


    @PostMapping("/{shipmentId}/request-departure")
    public ResponseEntity<String> requestShipDeparture(@PathVariable UUID shipmentId) {

        if (operationsPort.isDepartureAuthorized(shipmentId)){
            return ResponseEntity.status(HttpStatus.CREATED).body("the Ship is allowed to leave  ");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("this ship is not allowed to leave.");
        }

    }





}

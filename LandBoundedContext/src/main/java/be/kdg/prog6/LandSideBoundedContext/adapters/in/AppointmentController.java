package be.kdg.prog6.LandSideBoundedContext.adapters.in;

import be.kdg.prog6.LandSideBoundedContext.domain.Appointment;
import be.kdg.prog6.LandSideBoundedContext.domain.LicensePlate;
import be.kdg.prog6.LandSideBoundedContext.domain.SellerId;
import be.kdg.prog6.LandSideBoundedContext.dto.MakeAppointmentDto;
import be.kdg.prog6.LandSideBoundedContext.port.in.ScheduleAppointmentCommand;
import be.kdg.prog6.LandSideBoundedContext.port.in.ScheduleAppointmentUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController  {


    private final ModelMapper modelMapper;
    private ScheduleAppointmentUseCase scheduleAppointmentUseCase;

    public AppointmentController(ScheduleAppointmentUseCase scheduleAppointmentUseCase, ModelMapper modelMapper) {
        this.scheduleAppointmentUseCase = scheduleAppointmentUseCase;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<String> makeAppointmentForSeller(@RequestBody MakeAppointmentDto makeAppointmentDto) {

        try {
            ScheduleAppointmentCommand scheduleAppointmentCommand = new ScheduleAppointmentCommand(new LicensePlate(makeAppointmentDto.licensePlate()),
                    makeAppointmentDto.materialType(),  makeAppointmentDto.time(), new SellerId(java.util.UUID.fromString(makeAppointmentDto.sellerId())));
           Appointment appointment =  scheduleAppointmentUseCase.scheduleAppointment(scheduleAppointmentCommand);


            return ResponseEntity.ok(String.valueOf(appointment) + " \n the appointment has been made. \n");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

package be.kdg.prog6.LandSideBoundedContext.adapters.in;

import be.kdg.prog6.LandSideBoundedContext.dto.MakeAppointmentDto;
import be.kdg.prog6.LandSideBoundedContext.port.in.ScheduleAppointmentCommand;
import be.kdg.prog6.LandSideBoundedContext.port.in.ScheduleAppointmentPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/appointments")
public class AppointmentController  {


    private ScheduleAppointmentPort scheduleAppointmentPort;

    public AppointmentController(ScheduleAppointmentPort scheduleAppointmentPort) {
        this.scheduleAppointmentPort = scheduleAppointmentPort;
    }



    @PostMapping("/makeAppointment")
    public ResponseEntity<String> makeAppointmentForSeller(@RequestBody MakeAppointmentDto makeAppointmentDto) {

        try {
            LocalDate parsedDate = LocalDate.parse(makeAppointmentDto.date());

            ScheduleAppointmentCommand scheduleAppointmentCommand = new ScheduleAppointmentCommand(
                    makeAppointmentDto.licensePlate(), makeAppointmentDto.Payload(), makeAppointmentDto.materialType(), parsedDate, makeAppointmentDto.earliestArrivalTime(), makeAppointmentDto.latestArrivalTime() , makeAppointmentDto.companyName());

           ScheduleAppointmentCommand appointment =  scheduleAppointmentPort.scheduleAppointment(scheduleAppointmentCommand);

            return ResponseEntity.ok(String.valueOf(appointment) + " \n the appointment has been made. \n");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

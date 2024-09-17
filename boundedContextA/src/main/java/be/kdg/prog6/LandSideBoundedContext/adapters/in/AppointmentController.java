package be.kdg.prog6.LandSideBoundedContext.adapters.in;

import be.kdg.prog6.LandSideBoundedContext.Dto.ScheduleAppointmentCommand;
import be.kdg.prog6.LandSideBoundedContext.Port.in.ScheduleAppointmentPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
public class AppointmentController  {


    private ScheduleAppointmentPort scheduleAppointmentPort;

    public AppointmentController(ScheduleAppointmentPort scheduleAppointmentPort) {
        this.scheduleAppointmentPort = scheduleAppointmentPort;
    }


    @PostMapping("/makeAppointment")
    public ResponseEntity<String> makeAppointmentForSeller(@RequestBody ScheduleAppointmentCommand dto) {
        try {
            scheduleAppointmentPort.scheduleAppointment(dto);
            return ResponseEntity.ok("Appointment scheduled successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

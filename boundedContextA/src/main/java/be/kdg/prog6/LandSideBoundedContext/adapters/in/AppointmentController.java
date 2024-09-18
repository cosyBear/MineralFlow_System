package be.kdg.prog6.LandSideBoundedContext.adapters.in;

import be.kdg.prog6.LandSideBoundedContext.Port.in.ScheduleAppointmentCommand;
import be.kdg.prog6.LandSideBoundedContext.Port.in.ScheduleAppointmentPort;
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




    // curl -X POST "http://localhost:8091/api/appointments/makeAppointment/SELLER123/ABC-123/25.0/IRON/2024-10-15/10/11"
    @PostMapping("/makeAppointment/{sellerId}/{licensePlate}/{payload}/{materialType}/{date}/{earliestArrivalTime}/{latestArrivalTime}")
    public ResponseEntity<String> makeAppointmentForSeller(
            @PathVariable String sellerId,
            @PathVariable String licensePlate,
            @PathVariable double payload,
            @PathVariable String materialType,
            @PathVariable String date, // Use String and convert to LocalDate
            @PathVariable Integer earliestArrivalTime,
            @PathVariable Integer latestArrivalTime) {

        try {
            LocalDate parsedDate = LocalDate.parse(date);

            ScheduleAppointmentCommand scheduleAppointmentCommand = new ScheduleAppointmentCommand(
                    sellerId, licensePlate, payload, materialType, parsedDate, earliestArrivalTime, latestArrivalTime);

           ScheduleAppointmentCommand appointment =  scheduleAppointmentPort.scheduleAppointment(scheduleAppointmentCommand);

            return ResponseEntity.ok(String.valueOf(appointment) + " \n the appointment has been made. \n");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

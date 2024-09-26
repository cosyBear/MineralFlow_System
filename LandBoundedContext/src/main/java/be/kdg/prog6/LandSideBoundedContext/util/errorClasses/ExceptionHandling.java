package be.kdg.prog6.LandSideBoundedContext.util.errorClasses;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ExceptionHandling {



    @ExceptionHandler(TimeSlotFullException.class)
    public ResponseEntity<ErrorDto> handleTimeSlotConflict(TimeSlotFullException timeSlotFullException, HttpServletRequest request)
    {
         return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDto(timeSlotFullException.getMessage()));

    }

    @ExceptionHandler(AppointmentDontExist.class)
    public ResponseEntity<ErrorDto> handleAppointmentDontExist()
    {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto("Appointment Dont Exist"));

    }

}

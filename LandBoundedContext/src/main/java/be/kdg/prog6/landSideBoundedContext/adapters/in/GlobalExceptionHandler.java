package be.kdg.prog6.landSideBoundedContext.adapters.in;


import be.kdg.prog6.landSideBoundedContext.util.errorClasses.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(AppointmentDontExistException.class)
    public ResponseEntity<String> handleAppointmentDontExistException(AppointmentDontExistException e) {
        LOGGER.error("Appointment not found: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(WeighbridgeTicketNotFound.class)
    public ResponseEntity<String> handleWeighbridgeTicketNotFound(WeighbridgeTicketNotFound e) {
        LOGGER.error("Weighbridge ticket not found: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(WarehouseNotFoundException.class)
    public ResponseEntity<String> handleWarehouseNotFoundException(WarehouseNotFoundException e) {
        LOGGER.error("Warehouse not found: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(TimeSlotFullException.class)
    public ResponseEntity<String> handleTimeSlotFullException(TimeSlotFullException e) {
        LOGGER.error("Time slot full: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(TruckLateException.class)
    public ResponseEntity<String> handleTruckLateException(TruckLateException e) {
        LOGGER.error("Truck is not On TIME :::::{}", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(WarehouseIsFullException.class)
    public ResponseEntity<String> handleWarehouseIsFullException(WarehouseIsFullException e) {
        LOGGER.error("Warehouse is full: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(WarehouseDatabaseException.class)
    public ResponseEntity<String> handleWarehouseDatabaseException(WarehouseDatabaseException e) {
        LOGGER.error("Warehouse database error: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error: " + e.getMessage());
    }

    @ExceptionHandler(DatabaseOperationException.class)
    public ResponseEntity<String> handleDatabaseOperationException(DatabaseOperationException e) {
        LOGGER.error("Database operation error: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error: " + e.getMessage());
    }

    @ExceptionHandler(InsufficientMaterialException.class)
    public ResponseEntity<String> handleInsufficientMaterialException(InsufficientMaterialException e) {
        LOGGER.error("Insufficient material error: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }


}

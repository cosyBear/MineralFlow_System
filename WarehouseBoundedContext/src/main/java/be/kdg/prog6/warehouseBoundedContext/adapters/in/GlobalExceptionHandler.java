//package be.kdg.prog6.warehouseBoundedContext.adapters.in;
//
//
//import be.kdg.prog6.warehouseBoundedContext.util.Error.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
//
//    @ExceptionHandler(WarehouseNotFoundException.class)
//    public ResponseEntity<String> handleWarehouseNotFoundException(WarehouseNotFoundException e) {
//        LOGGER.error("Warehouse not found error: {}", e.getMessage());
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//    }
//
//    @ExceptionHandler(NotEnoughMaterialException.class)
//    public ResponseEntity<String> handleNoMaterialInWarehouseException(NotEnoughMaterialException e) {
//        LOGGER.error("No material in warehouse error: {}", e.getMessage());
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//    }
//
//    @ExceptionHandler(PurchaseOrderNotFoundException.class)
//    public ResponseEntity<String> handlePurchaseOrderNotFoundException(PurchaseOrderNotFoundException e) {
//        LOGGER.error("Purchase order not found: {}", e.getMessage());
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//    }
//
//    @ExceptionHandler(PurchaseOrderDatabaseException.class)
//    public ResponseEntity<String> handlePurchaseOrderDatabaseException(PurchaseOrderDatabaseException e) {
//        LOGGER.error("Purchase order database error: {}", e.getMessage());
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the purchase order.");
//    }
//
//    @ExceptionHandler(WarehouseDatabaseException.class)
//    public ResponseEntity<String> handleWarehouseDatabaseException(WarehouseDatabaseException e) {
//        LOGGER.error("Warehouse database error: {}", e.getMessage());
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while accessing the warehouse data.");
//    }
//
//    @ExceptionHandler(IllegalStateException.class)
//    public ResponseEntity<String> handleIllegalStateException(IllegalStateException e) {
//        LOGGER.error("Illegal state encountered: {}", e.getMessage());
//        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleGenericException(Exception e) {
//        LOGGER.error("Unexpected error: {}", e.getMessage());
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
//    }
//}
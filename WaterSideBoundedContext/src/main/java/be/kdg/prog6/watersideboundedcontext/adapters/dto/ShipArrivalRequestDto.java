package be.kdg.prog6.watersideboundedcontext.adapters.dto;


public record ShipArrivalRequestDto(String purchaseOrder , String shipmentOrder, String vesselNumber, String arrivalTime) {
}

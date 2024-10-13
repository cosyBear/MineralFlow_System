package be.kdg.prog6.landSideBoundedContext.domain;

public record WeighInDto(String licensePlate, double startWeight, MaterialType materialType, String sellerId,
                         String weighInTime) {

}

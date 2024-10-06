//package be.kdg.prog6.LandSideBoundedContext.port.out;
//
//import be.kdg.prog6.LandSideBoundedContext.domain.Bridge;
//import be.kdg.prog6.LandSideBoundedContext.domain.LicensePlate;
//import be.kdg.prog6.LandSideBoundedContext.domain.Id.SellerId;
//
//import java.time.LocalDate;
//import java.util.List;
//
//public interface BridgeLoadPort {
//
//    // Method to load a bridge by its ID
//    Bridge loadById(int bridgeId);
//
//    // Method to load all available (free) bridges
//    List<Bridge> loadAvailableBridges();
//
//    // Method to load all bridges assigned to a specific seller
//    List<Bridge> loadBySellerId(SellerId sellerId);
//
//    // Method to load all bridges (administrative purposes)
//    List<Bridge> loadAllBridges();
//
//    // Method to load bridges by date
//    List<Bridge> loadByDate(LocalDate date);
//
//    // Method to load a bridge by the current truck's license plate
//    Bridge loadByLicensePlate(LicensePlate licensePlate);
//
//}

//package be.kdg.prog6.LandSideBoundedContext.adapters.out.persistence.Bridage;
//
//import be.kdg.prog6.LandSideBoundedContext.adapters.out.entity.BridgeEntity;
//import be.kdg.prog6.LandSideBoundedContext.domain.LicensePlate;
//import be.kdg.prog6.LandSideBoundedContext.domain.SellerId;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.time.LocalDate;
//import java.util.List;
//
//public interface BridgeRepository extends JpaRepository<BridgeEntity, Integer> {
//
//    // Find available bridges (assuming you have an indicator in your entity for availability)
//    @Query("SELECT b FROM BridgeEntity b WHERE b.timeOut IS NOT NULL")
//    List<BridgeEntity> findAvailableBridges();
//
//    // Find bridges by SellerId (You may need to adjust for relationships if necessary)
//    List<BridgeEntity> findBySellerId(SellerId sellerId);
//
//    // Find bridges by date
//    @Query("SELECT b FROM BridgeEntity b WHERE b.timeIn >= :startOfDay AND b.timeIn <= :endOfDay")
//    List<BridgeEntity> findByDate(LocalDate startOfDay, LocalDate endOfDay);
//
//    // Find bridge by License Plate
//    BridgeEntity findByCurrentTruckLicensePlate(String currentTruckLicensePlate);
//
//
//}

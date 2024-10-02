//package be.kdg.prog6.LandSideBoundedContext.adapters.out.persistence.Bridage;
//
//import be.kdg.prog6.LandSideBoundedContext.adapters.out.entity.BridgeEntity;
//import be.kdg.prog6.LandSideBoundedContext.domain.Bridge;
//import be.kdg.prog6.LandSideBoundedContext.domain.LicensePlate;
//import be.kdg.prog6.LandSideBoundedContext.domain.SellerId;
//import be.kdg.prog6.LandSideBoundedContext.port.out.BridgeLoadPort;
//import be.kdg.prog6.LandSideBoundedContext.port.out.BridgeSavingPort;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.stream.Collectors;
//@Service
//public class BridageDatabaseAdapter implements BridgeLoadPort, BridgeSavingPort {
//
//    private final BridgeRepository bridgeRepository;
//
//    public BridageDatabaseAdapter(BridgeRepository bridgeRepository) {
//        this.bridgeRepository = bridgeRepository;
//    }
//
//    @Override
//    public Bridge loadById(int bridgeId) {
//        return bridgeRepository.findById(bridgeId)
//                .map(this::mapToDomain)
//                .orElse(null);
//    }
//
//    @Override
//    public List<Bridge> loadAvailableBridges() {
//        List<BridgeEntity> availableBridges = bridgeRepository.findAvailableBridges();
//        return availableBridges.stream()
//                .map(this::mapToDomain)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<Bridge> loadBySellerId(SellerId sellerId) {
//        List<BridgeEntity> bridges = bridgeRepository.findBySellerId(sellerId);
//        return bridges.stream()
//                .map(this::mapToDomain)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<Bridge> loadAllBridges() {
//        List<BridgeEntity> allBridges = bridgeRepository.findAll();
//        return allBridges.stream()
//                .map(this::mapToDomain)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<Bridge> loadByDate(LocalDate date) {
//        // Assuming the date is for a full day, we can calculate start and end of the day
//        LocalDate startOfDay = date.atStartOfDay().toLocalDate();
//        LocalDate endOfDay = date.plusDays(1).atStartOfDay().minusSeconds(1).toLocalDate();
//        List<BridgeEntity> bridgesByDate = bridgeRepository.findByDate(startOfDay, endOfDay);
//        return bridgesByDate.stream()
//                .map(this::mapToDomain)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public Bridge loadByLicensePlate(LicensePlate licensePlate) {
//        return bridgeRepository.findByCurrentTruckLicensePlate(licensePlate.licensePlate())
//                .map(this::mapToDomain)
//                .orElse(null);
//    }
//
//    @Override
//    public Bridge save(Bridge bridge) {
//        BridgeEntity entity = mapToEntity(bridge);
//        BridgeEntity savedEntity = bridgeRepository.save(entity);
//        return mapToDomain(savedEntity);
//    }
//
//    // Mapping method from entity to domain
//    private Bridge mapToDomain(BridgeEntity entity) {
//        Bridge bridge = new Bridge(entity.getId());
//        bridge.setCurrentTruckWeight(entity.getCurrentTruckWeight());
//        bridge.setCurrentTruckLicensePlate(new LicensePlate(entity.getCurrentTruckLicensePlate()));
//        bridge.setAssignedWarehouse(entity.getAssignedWarehouse());
//        bridge.setTimeIn(entity.getTimeIn());
//        bridge.setTimeOut(entity.getTimeOut());
//        return bridge;
//    }
//
//    // Mapping method from domain to entity
//    private BridgeEntity mapToEntity(Bridge bridge) {
//        BridgeEntity entity = new BridgeEntity();
//        entity.setBridgeId(String.valueOf(bridge.getBridgeId()));
//        entity.setCurrentTruckWeight(bridge.getCurrentTruckWeight());
//        entity.setCurrentTruckLicensePlate(bridge.getCurrentTruckLicensePlate().licensePlate());
//        entity.setAssignedWarehouse(bridge.getAssignedWarehouse());
//        entity.setTimeIn(bridge.getTimeIn());
//        entity.setTimeOut(bridge.getTimeOut());
//        return entity;
//    }
//}

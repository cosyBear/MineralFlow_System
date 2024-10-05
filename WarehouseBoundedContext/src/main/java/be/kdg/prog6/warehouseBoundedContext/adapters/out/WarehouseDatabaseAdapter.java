//package be.kdg.prog6.boundedcontextB.adapters.out;
//
//import be.kdg.prog6.boundedcontextB.adapters.out.JPAEntity.WarehouseEntity;
//import be.kdg.prog6.boundedcontextB.adapters.out.JPAEntity.WarehouseEventEntity;
//import be.kdg.prog6.boundedcontextB.adapters.out.persistence.WarehouseEventRepository;
//import be.kdg.prog6.boundedcontextB.adapters.out.persistence.Repository.WarehouseRepository;
//import be.kdg.prog6.boundedcontextB.domain.*;
//import be.kdg.prog6.boundedcontextB.port.out.LoadWareHousePort;
//import be.kdg.prog6.boundedcontextB.port.out.UpdateWarehousePort;
//import jakarta.transaction.Transactional;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class WarehouseDatabaseAdapter implements LoadWareHousePort , UpdateWarehousePort {
//
//
//    private  WarehouseRepository warehouseRepository;
//    private WarehouseEventRepository eventRepository;
//
//    public WarehouseDatabaseAdapter(WarehouseRepository warehouseRepository, WarehouseEventRepository eventRepository) {
//        this.warehouseRepository = warehouseRepository;
//        this.eventRepository = eventRepository;
//    }
//
//
//
//    @Transactional
//    @Override
//    public void saveWarehouse(Warehouse warehouse) {
//        // Save the warehouse entity itself
//        WarehouseEntity warehouseEntity = new WarehouseEntity(
//                warehouse.getWarehouseNumber(),
//                warehouse.getMaxCapacity(),
//                warehouse.getMaterialType(),
//                warehouse.getSellerId()
//        );
//        warehouseRepository.save(warehouseEntity);
//
//        // Save all new events
//        warehouse.getEventStream().forEach(event -> {
//            WarehouseEventEntity eventEntity = new WarehouseEventEntity(
//                    warehouseEntity,
//                    event.time(),
//                    event.type(),
//                    event.endWeight()
//            );
//            eventRepository.save(eventEntity);
//        });
//    }
//
//    @Override
//    public Warehouse findOrCreateWarehouse(SellerId sellerId, MaterialType materialType) {
//        Optional<WarehouseEntity> optionalWarehouse = warehouseRepository.findBySellerIdAndMaterialType(sellerId, materialType);
//
//        // If the warehouse already exists, map it to the domain and return
//        if (optionalWarehouse.isPresent()) {
//            return mapToDomain(optionalWarehouse.get());
//        }
//
//        // If a warehouse is available with no assigned seller or material type, assign it
//        Optional<WarehouseEntity> availableWarehouse = warehouseRepository.findFirstBySellerIdIsNullAndMaterialTypeIsNull();
//        if (availableWarehouse.isPresent()) {
//            WarehouseEntity warehouseEntity = availableWarehouse.get();
//            warehouseEntity.setSellerId(sellerId);
//            warehouseEntity.setMaterialType(materialType);
//            warehouseRepository.save(warehouseEntity);
//            return mapToDomain(warehouseEntity);
//        }
//
//        // If no warehouse exists, create a new one
//        WarehouseEntity newWarehouseEntity = new WarehouseEntity(
//                (int) (warehouseRepository.count() + 1), // Generate new warehouse number
//                10000.0, // Default capacity
//                materialType,
//                sellerId
//        );
//        warehouseRepository.save(newWarehouseEntity);
//
//        return mapToDomain(newWarehouseEntity);
//    }
//
//}
//
//
//
//
//
//
//
//

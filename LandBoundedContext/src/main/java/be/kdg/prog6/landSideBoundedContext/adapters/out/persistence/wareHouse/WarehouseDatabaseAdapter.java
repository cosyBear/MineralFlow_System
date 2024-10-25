package be.kdg.prog6.landSideBoundedContext.adapters.out.persistence.wareHouse;

import be.kdg.prog6.landSideBoundedContext.adapters.out.entity.WareHouseEntity;
import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import be.kdg.prog6.landSideBoundedContext.domain.Warehouse;
import be.kdg.prog6.landSideBoundedContext.port.out.WarehouseLoadPort;
import be.kdg.prog6.landSideBoundedContext.port.out.WarehouseSavePort;
import be.kdg.prog6.landSideBoundedContext.util.errorClasses.WarehouseDatabaseException;
import be.kdg.prog6.landSideBoundedContext.util.errorClasses.WarehouseNotFoundException;
import domain.MaterialType;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WarehouseDatabaseAdapter implements WarehouseLoadPort, WarehouseSavePort {

    private final  WareHouseRepository warehouseRepo;
    private final ModelMapper modelMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseDatabaseAdapter.class);

    public WarehouseDatabaseAdapter(WareHouseRepository warehouseRepo, @Qualifier("landModelMapper")ModelMapper modelMapper) {
        this.warehouseRepo = warehouseRepo;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<Warehouse> findAllBySellerId(UUID sellerId) {
        try {
            List<Warehouse> warehouseList = new ArrayList<>();
            for (WareHouseEntity warehouseEntity : warehouseRepo.findAllBySellerId(sellerId)) {
                warehouseList.add(modelMapper.map(warehouseEntity, Warehouse.class));
            }
            return warehouseList;
        } catch (Exception e) {
            LOGGER.error("Failed to find warehouses by seller ID: {}", sellerId, e);
            throw new WarehouseDatabaseException("Database error: Could not retrieve warehouses for seller ID " + sellerId +  e);
        }
    }

    @Override
    public Warehouse findBySellerIdAAndMaterialType(SellerId sellerId, MaterialType materialType) {
        try {
            WareHouseEntity warehouseEntity = warehouseRepo.findBySellerIdAndMaterialType(sellerId.id(), materialType);
            if (warehouseEntity == null) {
                throw new WarehouseNotFoundException("Warehouse not found for seller ID: " + sellerId + " and material type: " + materialType);
            }
            return modelMapper.map(warehouseEntity, Warehouse.class);
        } catch (Exception e) {
            LOGGER.error("Failed to find warehouse by seller ID and material type: {} {}", sellerId, materialType, e);
            throw new WarehouseDatabaseException("Database error: Could not retrieve warehouse for seller ID " + sellerId + " and material type " + materialType + " " +  e);
        }
    }

    @Override
    public Optional<Warehouse> findById(UUID id) {
        try {
            return warehouseRepo.findById(id)
                    .map(warehouseEntity -> {
                        LOGGER.info("Found warehouse: {}", warehouseEntity);
                        return modelMapper.map(warehouseEntity, Warehouse.class);
                    });
        } catch (Exception e) {
            LOGGER.error("Failed to find warehouse by ID: {}", id, e);
            throw new WarehouseDatabaseException("Database error: Could not retrieve warehouse for ID " + id +  e);
        }
    }

    @Override
    public List<Warehouse> warehouseOverview() {
        try {
            List<Warehouse> warehouseList = new ArrayList<>();
            for (WareHouseEntity warehouseEntity : warehouseRepo.findAll()) {
                warehouseList.add(modelMapper.map(warehouseEntity, Warehouse.class));
            }
            return warehouseList;
        } catch (Exception e) {
            LOGGER.error("Failed to retrieve warehouse overview", e);
            throw new WarehouseDatabaseException("Database error: Could not retrieve warehouse overview" +  e);
        }
    }
    @Override
    public void Save(Warehouse warehouse) {
        try {
            WareHouseEntity wareHouseEntity = modelMapper.map(warehouse, WareHouseEntity.class);
            warehouseRepo.save(wareHouseEntity);
        } catch (Exception e) {
            LOGGER.error("Failed to save warehouse: {}", warehouse, e);
            throw new WarehouseDatabaseException("Database error: Could not save warehouse" + e);
        }
    }
}

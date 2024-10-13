package be.kdg.prog6.landSideBoundedContext.adapters.out.persistence.wareHouse;

import be.kdg.prog6.landSideBoundedContext.adapters.out.entity.WareHouseEntity;
import be.kdg.prog6.landSideBoundedContext.domain.MaterialType;
import be.kdg.prog6.landSideBoundedContext.domain.WareHouse;
import be.kdg.prog6.landSideBoundedContext.port.out.WarehouseLoadPort;
import be.kdg.prog6.landSideBoundedContext.port.out.WarehouseSavePort;
import be.kdg.prog6.landSideBoundedContext.util.errorClasses.WareHouseNotFound;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public WarehouseDatabaseAdapter(WareHouseRepository warehouseRepo, ModelMapper modelMapper) {
        this.warehouseRepo = warehouseRepo;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<WareHouse> findAllBySellerId(UUID sellerId) {

        List<WareHouse> warehouseList = new ArrayList<>();
        for(WareHouseEntity warehouseEntity : warehouseRepo.findAllBySellerId(sellerId)) {
            warehouseList.add(modelMapper.map(warehouseEntity, WareHouse.class));
        }
        return warehouseList;
    }

    @Override
    public WareHouse findBySellerId(UUID sellerId) {
        return modelMapper.map(warehouseRepo.findBySellerId(sellerId), WareHouse.class);
    }

    @Override
    public WareHouse findBySellerIdAAndMaterialType(UUID sellerId, MaterialType materialType) {
        return modelMapper.map(warehouseRepo.findBySellerIdAndMaterialType(sellerId, materialType), WareHouse.class);
    }

    @Override
    public Optional<WareHouse> findById(UUID id) {
        return warehouseRepo.findById(id)
                .map(warehouseEntity -> {
                    LOGGER.info(warehouseEntity.toString());
                    return modelMapper.map(warehouseEntity, WareHouse.class);
                });
    }

    @Override
    public List<WareHouse> warehouseOverview() {
        List<WareHouse> wareHouseList = new ArrayList<>();

        for(WareHouseEntity warehouseEntity : warehouseRepo.findAll()) {
            wareHouseList.add(modelMapper.map(warehouseEntity, WareHouse.class));
        }
        return wareHouseList;

    }

    @Override
    public void Save(WareHouse warehouse) {
        WareHouseEntity wareHouseEntity = modelMapper.map(warehouse, WareHouseEntity.class);
        warehouseRepo.save(wareHouseEntity);
    }
}

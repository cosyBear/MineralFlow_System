package be.kdg.prog6.landSideBoundedContext.adapters.out.persistence.wareHouse;

import be.kdg.prog6.landSideBoundedContext.adapters.out.entity.WareHouseEntity;
import be.kdg.prog6.landSideBoundedContext.domain.MaterialType;
import be.kdg.prog6.landSideBoundedContext.domain.WareHouse;
import be.kdg.prog6.landSideBoundedContext.port.out.WarehouseLoadPort;
import be.kdg.prog6.landSideBoundedContext.port.out.WarehouseSavePort;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class WarehouseDatabaseAdapter implements WarehouseLoadPort, WarehouseSavePort {

    private final  WareHouseRepository warehouseRepo;
    private final ModelMapper modelMapper;

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
    public void Save(WareHouse warehouse) {
        warehouseRepo.save(modelMapper.map(warehouse, WareHouseEntity.class));
    }
}

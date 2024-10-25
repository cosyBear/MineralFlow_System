package be.kdg.prog6.landSideBoundedContext.adapters.out.persistence.wareHouse;

import be.kdg.prog6.landSideBoundedContext.adapters.out.entity.WareHouseEntity;
import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import be.kdg.prog6.landSideBoundedContext.domain.Warehouse;
import be.kdg.prog6.landSideBoundedContext.port.out.WarehouseLoadPort;
import be.kdg.prog6.landSideBoundedContext.port.out.WarehouseSavePort;
import domain.MaterialType;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
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

        List<Warehouse> warehouseList = new ArrayList<>();
        for(WareHouseEntity warehouseEntity : warehouseRepo.findAllBySellerId(sellerId)) {
            warehouseList.add(modelMapper.map(warehouseEntity, Warehouse.class));
        }
        return warehouseList;
    }

    @Override
    public Warehouse findBySellerId(UUID sellerId) {
        return modelMapper.map(warehouseRepo.findBySellerId(sellerId), Warehouse.class);
    }

    @Override
    public Warehouse findBySellerIdAAndMaterialType(SellerId sellerId, MaterialType materialType) {
        // make an ecption to handle if the seller dont exist.
        return modelMapper.map(warehouseRepo.findBySellerIdAndMaterialType(sellerId.id(), materialType), Warehouse.class);
    }

    @Override
    public Optional<Warehouse> findById(UUID id) {
        return warehouseRepo.findById(id)
                .map(warehouseEntity -> {
                    LOGGER.info(warehouseEntity.toString());
                    return modelMapper.map(warehouseEntity, Warehouse.class);
                });
    }

    @Override
    public List<Warehouse> warehouseOverview() {
        List<Warehouse> warehouseList = new ArrayList<>();

        for(WareHouseEntity warehouseEntity : warehouseRepo.findAll()) {
            warehouseList.add(modelMapper.map(warehouseEntity, Warehouse.class));
        }
        return warehouseList;

    }

    @Override
    public void Save(Warehouse warehouse) {
        WareHouseEntity wareHouseEntity = modelMapper.map(warehouse, WareHouseEntity.class);
        warehouseRepo.save(wareHouseEntity);
    }
}

package be.kdg.prog6.boundedcontextB.adapters.out.persistence.Adapters;


import be.kdg.prog6.boundedcontextB.adapters.out.JPAEntity.WarehouseEntity;
import be.kdg.prog6.boundedcontextB.adapters.out.persistence.Repository.WarehouseRepository;
import be.kdg.prog6.boundedcontextB.domain.Warehouse;
import be.kdg.prog6.boundedcontextB.domain.WarehouseId;
import be.kdg.prog6.boundedcontextB.domain.SellerId;
import be.kdg.prog6.boundedcontextB.port.out.Warehouse.WarehouseLoadPort;
import be.kdg.prog6.boundedcontextB.port.out.Warehouse.WarehouseSavePort;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Component
public class WarehouseDataBaseAdapter implements WarehouseLoadPort , WarehouseSavePort {

    private final WarehouseRepository warehouseRepository;
    private final ModelMapper modelMapper;

    public WarehouseDataBaseAdapter(WarehouseRepository warehouseRepository, ModelMapper modelMapper) {
        this.warehouseRepository = warehouseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Warehouse save(Warehouse warehouse) {
        var warehouseEntity = modelMapper.map(warehouse, WarehouseEntity.class);
        warehouseEntity = warehouseRepository.save(warehouseEntity);
        return modelMapper.map(warehouseEntity, Warehouse.class);
    }

    @Override
    public Optional<Warehouse> findBySellerId(SellerId sellerId) {
        return warehouseRepository.findBySellerId(sellerId)
                .map(entity -> modelMapper.map(entity, Warehouse.class));
    }

    @Override
    public Optional<Warehouse> findByWarehouseNumber(WarehouseId warehouseId) {
        return warehouseRepository.findByWarehouseNumber(warehouseId)
                .map(entity -> modelMapper.map(entity, Warehouse.class));
    }

    @Override
    public Warehouse update(Warehouse warehouse) {
        // Simply re-save the updated WarehouseEntity
        var warehouseEntity = modelMapper.map(warehouse, WarehouseEntity.class);
        warehouseEntity = warehouseRepository.save(warehouseEntity);
        return modelMapper.map(warehouseEntity, Warehouse.class);
    }
}


package be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence;


import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEntity;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence.Repository.WarehouseRepository;
import be.kdg.prog6.warehouseBoundedContext.domain.MaterialType;
import be.kdg.prog6.warehouseBoundedContext.domain.Warehouse;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseId;
import be.kdg.prog6.warehouseBoundedContext.domain.SellerId;
import be.kdg.prog6.warehouseBoundedContext.port.out.Warehouse.WarehouseLoadPort;
import be.kdg.prog6.warehouseBoundedContext.port.out.Warehouse.WarehouseSavePort;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

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
    public Warehouse findBySellerIdAndMaterialType(SellerId sellerId , MaterialType materialType){
        Optional<WarehouseEntity> warehouseEntity = warehouseRepository.findBySellerIdAndMaterialType(sellerId , materialType);
        return (modelMapper.map(warehouseEntity, Warehouse.class));
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


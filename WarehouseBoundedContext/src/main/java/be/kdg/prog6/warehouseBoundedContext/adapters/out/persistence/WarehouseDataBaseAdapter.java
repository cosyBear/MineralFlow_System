package be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence;

import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEntity;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEventsWindowEntity;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEventEntity;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence.Repository.WarehouseRepository;
import be.kdg.prog6.warehouseBoundedContext.domain.*;
import be.kdg.prog6.warehouseBoundedContext.port.out.Warehouse.WarehouseLoadPort;
import be.kdg.prog6.warehouseBoundedContext.port.out.Warehouse.WarehouseSavePort;
import be.kdg.prog6.warehouseBoundedContext.util.Mapper.WarehouseMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class WarehouseDataBaseAdapter implements WarehouseLoadPort, WarehouseSavePort {


    private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper warehouseMapper;

    public WarehouseDataBaseAdapter(WarehouseRepository warehouseRepository, WarehouseMapper warehouseMapper) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseMapper = warehouseMapper;
    }

    @Override
    @Transactional
    public Warehouse findBySellerIdAndMaterialType(SellerId sellerId, MaterialType materialType) {
        Optional<WarehouseEntity> warehouseEntityOptional =
                warehouseRepository.findBySellerIdAndMaterialType(sellerId, materialType);
        if (warehouseEntityOptional.isPresent()) {
            return warehouseMapper.toDomain(warehouseEntityOptional.get());
        } else {
            throw new EntityNotFoundException("Warehouse not found for sellerId: " + sellerId +
                    " and materialType: " + materialType);
        }
    }

    @Override
    @Transactional
    public Warehouse save(Warehouse warehouse) {
        WarehouseEntity warehouseEntity = warehouseMapper.toEntity(warehouse);
        warehouseEntity = warehouseRepository.save(warehouseEntity);
        return warehouseMapper.toDomain(warehouseEntity);
    }

    @Override
    public Optional<Warehouse> findBySellerId(SellerId sellerId) {
        return warehouseRepository.findBySellerId(sellerId)
                .map(warehouseMapper::toDomain);
    }

    @Override
    public Optional<Warehouse> findByWarehouseNumber(WarehouseId warehouseId) {
        return warehouseRepository.findByWarehouseNumber(warehouseId)
                .map(warehouseMapper::toDomain);
    }

    @Override
    public Warehouse update(Warehouse warehouse) {
        WarehouseEntity warehouseEntity = warehouseMapper.toEntity(warehouse);
        warehouseEntity = warehouseRepository.save(warehouseEntity);
        return warehouseMapper.toDomain(warehouseEntity);
    }
}

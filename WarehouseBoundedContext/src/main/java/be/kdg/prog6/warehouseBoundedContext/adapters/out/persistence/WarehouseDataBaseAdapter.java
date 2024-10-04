package be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence;

import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEntity;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEventEntity;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEventsWindowEntity;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence.Repository.WarehouseEventEntityRepository;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence.Repository.WarehouseEventsWindowEntityRepository;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence.Repository.WarehouseRepository;
import be.kdg.prog6.warehouseBoundedContext.domain.*;
import be.kdg.prog6.warehouseBoundedContext.port.out.Warehouse.WarehouseLoadPort;
import be.kdg.prog6.warehouseBoundedContext.port.out.Warehouse.WarehouseSavePort;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class WarehouseDataBaseAdapter implements WarehouseLoadPort, WarehouseSavePort {

    private final WarehouseRepository warehouseRepository;
    private WarehouseEventEntityRepository warehouseEventEntityRepository;
    private WarehouseEventsWindowEntityRepository warehouseEventsWindowEntityRepository;
    private final ModelMapper modelMapper;

    public WarehouseDataBaseAdapter(ModelMapper modelMapper, WarehouseEventsWindowEntityRepository warehouseEventsWindowEntityRepository, WarehouseEventEntityRepository warehouseEventEntityRepository, WarehouseRepository warehouseRepository) {
        this.modelMapper = modelMapper;
        this.warehouseEventsWindowEntityRepository = warehouseEventsWindowEntityRepository;
        this.warehouseEventEntityRepository = warehouseEventEntityRepository;
        this.warehouseRepository = warehouseRepository;
    }


    @Override
    @Transactional
    public Warehouse fetchWarehouseWithEvents(UUID warehouseId) {
        return modelMapper.map(warehouseRepository.fetchWarehouseWithEvents(warehouseId), Warehouse.class);

    }

    @Override
    public Warehouse findBySellerId(SellerId sellerId) {
        return modelMapper.map(warehouseRepository.findBySellerId(sellerId), Warehouse.class);

    }

    @Override
    public Warehouse findBySellerIdAndWarehouseId(SellerId sellerId, UUID warehouseId) {
        return modelMapper.map(warehouseRepository.findBySellerIdAndWarehouseId(sellerId, warehouseId), Warehouse.class);
    }

    @Override
    public Warehouse findBySellerIdAndMaterialType(SellerId sellerId, MaterialType materialType) {
        return modelMapper.map(warehouseRepository.findBySellerIdAndMaterialType(sellerId, materialType), Warehouse.class);
    }

    @Override
    @Transactional
    public void save(Warehouse warehouse) {
        WarehouseEntity warehouseEntity = modelMapper.map(warehouse, WarehouseEntity.class);

        warehouseRepository.save(warehouseEntity);

    }


}

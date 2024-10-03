package be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence.Repository;


import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEntity;
import be.kdg.prog6.warehouseBoundedContext.domain.MaterialType;
import be.kdg.prog6.warehouseBoundedContext.domain.SellerId;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WarehouseRepository extends JpaRepository<WarehouseEntity, UUID> {


        Optional<WarehouseEntity> findBySellerId(SellerId sellerId);

        Optional<WarehouseEntity> findByWarehouseNumber(WarehouseId warehouseNumber);

        Optional<WarehouseEntity> findBySellerIdAndMaterialType(SellerId sellerId , MaterialType materialType);



}


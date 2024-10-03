package be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence.Repository;


import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEntity;
import be.kdg.prog6.warehouseBoundedContext.domain.MaterialType;
import be.kdg.prog6.warehouseBoundedContext.domain.SellerId;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.UUID;

public interface WarehouseRepository extends JpaRepository<WarehouseEntity, UUID> {



        @Query("SELECT w FROM WarehouseEntity w LEFT JOIN FETCH w.warehouseEventsWindowEntity WHERE w.sellerId = :sellerId")
        Optional<WarehouseEntity> findBySellerId(@Param("sellerId") SellerId sellerId);

        @Query("SELECT w FROM WarehouseEntity w LEFT JOIN FETCH w.warehouseEventsWindowEntity WHERE w.warehouseNumber = :warehouseNumber")
        Optional<WarehouseEntity> findByWarehouseNumber(@Param("warehouseNumber") WarehouseId warehouseNumber);


// In WarehouseRepository.java

        @Query("SELECT DISTINCT w FROM WarehouseEntity w " +
                "LEFT JOIN FETCH w.warehouseEventsWindowEntity we " +
                "LEFT JOIN FETCH we.warehouseEventList " +
                "WHERE w.sellerId = :sellerId AND w.materialType = :materialType")
        Optional<WarehouseEntity> findBySellerIdAndMaterialType(
                @Param("sellerId") SellerId sellerId,
                @Param("materialType") MaterialType materialType
        );



}


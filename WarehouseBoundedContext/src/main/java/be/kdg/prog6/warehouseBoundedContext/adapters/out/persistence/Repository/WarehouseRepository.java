package be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence.Repository;


import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEntity;
import be.kdg.prog6.warehouseBoundedContext.domain.SellerId;
import domain.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface WarehouseRepository extends JpaRepository<WarehouseEntity, UUID> {

    @Query("SELECT w FROM WarehouseEntity w " +
            "LEFT JOIN FETCH w.warehouseEventsWindow wew " +
            "LEFT JOIN FETCH wew.warehouseEventList " +
            "WHERE w.warehouseId = :warehouseId")
    WarehouseEntity fetchWarehouseWithEvents(@Param("warehouseId") UUID warehouseId);

    @Query("SELECT w FROM WarehouseEntity w " +
            "LEFT JOIN FETCH w.warehouseEventsWindow wew " +
            "LEFT JOIN FETCH wew.warehouseEventList " +
            "WHERE w.sellerId = :sellerId")
    WarehouseEntity findBySellerId(@Param("sellerId") SellerId sellerId);

    @Query("SELECT w FROM WarehouseEntity w " +
            "LEFT JOIN FETCH w.warehouseEventsWindow wew " +
            "LEFT JOIN FETCH wew.warehouseEventList " +
            "WHERE w.sellerId = :sellerId AND w.warehouseId = :warehouseId")
    WarehouseEntity findBySellerIdAndWarehouseId(
            @Param("sellerId") SellerId sellerId,
            @Param("warehouseId") UUID warehouseId);

    @Query("SELECT w FROM WarehouseEntity w LEFT JOIN FETCH w.warehouseEventsWindow e LEFT JOIN FETCH e.warehouseEventList")
    List<WarehouseEntity> loadAllWarehouse();

    @Query("SELECT w FROM WarehouseEntity w LEFT JOIN FETCH w.warehouseEventsWindow e LEFT JOIN FETCH e.warehouseEventList WHERE w.sellerId = :sellerId AND w.materialType = :materialType")
    WarehouseEntity findBySellerIdAndMaterialType(@Param("sellerId") UUID sellerId, @Param("materialType") MaterialType materialType);


}

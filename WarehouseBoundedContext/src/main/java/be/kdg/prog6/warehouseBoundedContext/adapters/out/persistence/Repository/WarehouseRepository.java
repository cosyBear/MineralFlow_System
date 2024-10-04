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

        // Custom query to fetch warehouse along with its events window and all events
        @Query("SELECT w FROM WarehouseEntity w " +
                "LEFT JOIN FETCH w.warehouseEventsWindow wew " +
                "LEFT JOIN FETCH wew.eventList " +
                "WHERE w.warehouseId = :warehouseId")
        WarehouseEntity fetchWarehouseWithEvents(@Param("warehouseId") UUID warehouseId);

        // Find by sellerId only
        @Query("SELECT w FROM WarehouseEntity w " +
                "LEFT JOIN FETCH w.warehouseEventsWindow wew " +
                "LEFT JOIN FETCH wew.eventList " +
                "WHERE w.sellerId = :sellerId")
        WarehouseEntity findBySellerId(@Param("sellerId") SellerId sellerId);

        // Find by sellerId and warehouseId
        @Query("SELECT w FROM WarehouseEntity w " +
                "LEFT JOIN FETCH w.warehouseEventsWindow wew " +
                "LEFT JOIN FETCH wew.eventList " +
                "WHERE w.sellerId = :sellerId AND w.warehouseId = :warehouseId")
        WarehouseEntity findBySellerIdAndWarehouseId(
                @Param("sellerId") SellerId sellerId,
                @Param("warehouseId") UUID warehouseId);

        // Find by sellerId and materialType
        @Query("SELECT w FROM WarehouseEntity w " +
                "LEFT JOIN FETCH w.warehouseEventsWindow wew " +
                "LEFT JOIN FETCH wew.eventList " +
                "WHERE w.sellerId = :sellerId AND w.materialType = :materialType")
        WarehouseEntity findBySellerIdAndMaterialType(
                @Param("sellerId") SellerId sellerId,
                @Param("materialType") MaterialType materialType);


}

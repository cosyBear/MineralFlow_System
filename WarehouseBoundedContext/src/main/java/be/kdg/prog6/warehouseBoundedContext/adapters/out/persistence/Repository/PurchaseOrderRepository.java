package be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence.Repository;


import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.PurchaseOrderEntity;
import domain.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrderEntity, UUID> {

    List<PurchaseOrderEntity> findBySellerId(UUID sellerId);

    List<PurchaseOrderEntity> findByCustomerName(String customerName);

    @Query("select p from PurchaseOrderEntity p left join fetch p.purchaseOrderLines line " +
            "where p.sellerId = :sellerId and line.materialType = :materialType")
    List<PurchaseOrderEntity> findBySellerIdAndMaterialType(@Param("sellerId") UUID sellerId, @Param("materialType") MaterialType materialType);


    @Query("select p from PurchaseOrderEntity p left join fetch p.purchaseOrderLines line where p.purchaseOrderId = :purchaseOrderId")
    PurchaseOrderEntity getPurchaseOrderByPurchaseOrderId(@Param("purchaseOrderId") UUID purchaseOrderId);

    @Query("select p from PurchaseOrderEntity as p left join fetch p.purchaseOrderLines as line")
    List<PurchaseOrderEntity> getAll();
}

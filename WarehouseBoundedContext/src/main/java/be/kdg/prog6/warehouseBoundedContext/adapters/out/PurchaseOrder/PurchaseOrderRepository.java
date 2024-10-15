package be.kdg.prog6.warehouseBoundedContext.adapters.out.PurchaseOrder;


import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.PurchaseOrderEntity;
import domain.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrderEntity, UUID> {

    // Find all PurchaseOrders by sellerId
    List<PurchaseOrderEntity> findBySellerId(UUID sellerId);

    // Find PurchaseOrders by material type
    List<PurchaseOrderEntity> findByMaterialType(MaterialType materialType);

    // Find PurchaseOrder by customer name
    List<PurchaseOrderEntity> findByCustomerName(String customerName);

    // Find PurchaseOrder by both sellerId and material type
    List<PurchaseOrderEntity> findBySellerIdAndMaterialType(UUID sellerId, MaterialType materialType);

    PurchaseOrderEntity getPurchaseOrderByPurchaseOrderId(UUID id);
}

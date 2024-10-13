package be.kdg.prog6.landSideBoundedContext.adapters.out.persistence.PurchaseOrder;


import be.kdg.prog6.landSideBoundedContext.adapters.out.entity.PurchaseOrderEntity;
import be.kdg.prog6.landSideBoundedContext.domain.MaterialType;
import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
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

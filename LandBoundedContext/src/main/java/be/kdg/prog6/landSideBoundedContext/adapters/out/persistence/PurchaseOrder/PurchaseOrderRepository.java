package be.kdg.prog6.landSideBoundedContext.adapters.out.persistence.PurchaseOrder;


import be.kdg.prog6.landSideBoundedContext.adapters.out.entity.PurchaseOrderEntity;
import be.kdg.prog6.landSideBoundedContext.domain.MaterialType;
import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrderEntity, Integer> {

    // Find all PurchaseOrders by sellerId
    List<PurchaseOrderEntity> findBySellerId(SellerId sellerId);

    // Find PurchaseOrders by material type
    List<PurchaseOrderEntity> findByMaterialType(MaterialType materialType);

    // Find PurchaseOrder by customer name
    List<PurchaseOrderEntity> findByCustomerName(String customerName);

    // Find PurchaseOrder by both sellerId and material type
    List<PurchaseOrderEntity> findBySellerIdAndMaterialType(SellerId sellerId, MaterialType materialType);

    PurchaseOrderEntity getPurchaseOrderByPurchaseOrderId(Integer id);
}

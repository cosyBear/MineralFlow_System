package be.kdg.prog6.landSideBoundedContext.port.out;

import be.kdg.prog6.landSideBoundedContext.domain.MaterialType;
import be.kdg.prog6.landSideBoundedContext.domain.PurchaseOrder;
import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;

import java.util.List;
import java.util.UUID;

public interface PurchaseOrderLoadPort {

    // Load a single PurchaseOrder by its ID
    PurchaseOrder loadById(UUID purchaseOrderId);

    // Load all PurchaseOrders by a SellerId
    List<PurchaseOrder> loadBySellerId(SellerId sellerId);

    // Load all PurchaseOrders by material type
    List<PurchaseOrder> loadByMaterialType(MaterialType materialType);

    // Load all PurchaseOrders by customer name
    List<PurchaseOrder> loadByCustomerName(String customerName);

    // Load PurchaseOrders by both SellerId and MaterialType
    List<PurchaseOrder> loadBySellerIdAndMaterialType(SellerId sellerId, MaterialType materialType);


}

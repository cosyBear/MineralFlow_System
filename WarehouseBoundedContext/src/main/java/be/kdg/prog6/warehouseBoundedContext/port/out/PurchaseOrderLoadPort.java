package be.kdg.prog6.warehouseBoundedContext.port.out;

import be.kdg.prog6.warehouseBoundedContext.domain.PurchaseOrder;
import be.kdg.prog6.warehouseBoundedContext.domain.SellerId;
import domain.MaterialType;

import java.util.List;
import java.util.UUID;

public interface PurchaseOrderLoadPort {

    PurchaseOrder loadById(UUID purchaseOrderId);

    List<PurchaseOrder> loadBySellerId(SellerId sellerId);

    List<PurchaseOrder> loadByCustomerName(String customerName);

    List<PurchaseOrder> loadBySellerIdAndMaterialType(SellerId sellerId, MaterialType materialType);

    List<PurchaseOrder> getAllPurchaseOrdersStatus();

}

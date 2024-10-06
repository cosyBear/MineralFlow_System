package be.kdg.prog6.landSideBoundedContext.adapters.out.persistence.PurchaseOrder;

import be.kdg.prog6.landSideBoundedContext.adapters.out.entity.PurchaseOrderEntity;
import be.kdg.prog6.landSideBoundedContext.domain.MaterialType;
import be.kdg.prog6.landSideBoundedContext.domain.PurchaseOrder;
import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import be.kdg.prog6.landSideBoundedContext.port.out.PurchaseOrderLoadPort;
import be.kdg.prog6.landSideBoundedContext.port.out.PurchaseOrderSavePort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class PurchaseOrderDataBaseAdapter implements PurchaseOrderLoadPort , PurchaseOrderSavePort {




    private final PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseOrderDataBaseAdapter(PurchaseOrderRepository purchaseOrderRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    public PurchaseOrder getPurchaseOrderById(int purchaseOrderId) {
        return purchaseOrderRepository.findById(purchaseOrderId)
                .map(this::mapToDomain)
                .orElse(null);
    }

    @Override
    public PurchaseOrder loadById(int purchaseOrderId) {
        return getPurchaseOrderById(purchaseOrderId);
    }

    @Override
    public PurchaseOrder save(PurchaseOrder purchaseOrder) {
        PurchaseOrderEntity entity = mapToEntity(purchaseOrder);
        PurchaseOrderEntity savedEntity = purchaseOrderRepository.save(entity);
        return mapToDomain(savedEntity);
    }

    // Example method for loading PurchaseOrders by SellerId
    public List<PurchaseOrder> loadBySellerId(SellerId sellerId) {
        List<PurchaseOrderEntity> entities = purchaseOrderRepository.findBySellerId(sellerId); // Convert SellerId to UUID
        return entities.stream().map(this::mapToDomain).toList(); // Map entities to domain objects
    }

    // Example method for loading PurchaseOrders by MaterialType
    public List<PurchaseOrder> loadByMaterialType(MaterialType materialType) {
        List<PurchaseOrderEntity> entities = purchaseOrderRepository.findByMaterialType(materialType);
        return entities.stream().map(this::mapToDomain).toList();
    }

    // Example method for loading PurchaseOrders by Customer Name
    public List<PurchaseOrder> loadByCustomerName(String customerName) {
        List<PurchaseOrderEntity> entities = purchaseOrderRepository.findByCustomerName(customerName);
        return entities.stream().map(this::mapToDomain).toList();
    }

    // Example method for loading PurchaseOrders by SellerId and MaterialType
    public List<PurchaseOrder> loadBySellerIdAndMaterialType(SellerId sellerId, MaterialType materialType) {
        List<PurchaseOrderEntity> entities = purchaseOrderRepository.findBySellerIdAndMaterialType(sellerId, materialType);
        return entities.stream().map(this::mapToDomain).toList();
    }

    // Mapping methods for converting between domain and entity
    private PurchaseOrder mapToDomain(PurchaseOrderEntity entity) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setOrderDate(entity.getOrderDate());
        purchaseOrder.setPurchaseOrderNumber(entity.getPurchaseOrderId());
        purchaseOrder.setSellerId(new SellerId(entity.getSellerId())); // Convert from String to SellerId
        purchaseOrder.setCustomerName(entity.getCustomerName());
        purchaseOrder.setMaterialType(entity.getMaterialType());
        purchaseOrder.setAmountOfMaterialInTons(entity.getAmountOfMaterialInTons());
        return purchaseOrder;
    }

    private PurchaseOrderEntity mapToEntity(PurchaseOrder purchaseOrder) {
        PurchaseOrderEntity entity = new PurchaseOrderEntity();
        entity.setOrderDate(purchaseOrder.getOrderDate());
        entity.setPurchaseOrderId(purchaseOrder.getPurchaseOrderNumber());
        entity.setSellerId(purchaseOrder.getSellerId().id());
        entity.setCustomerName(purchaseOrder.getCustomerName());
        entity.setMaterialType(purchaseOrder.getMaterialType());
        entity.setAmountOfMaterialInTons(purchaseOrder.getAmountOfMaterialInTons());
        return entity;
    }



}

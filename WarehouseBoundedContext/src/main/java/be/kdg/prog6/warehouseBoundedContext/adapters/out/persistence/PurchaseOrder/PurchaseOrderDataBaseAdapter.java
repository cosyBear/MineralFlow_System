package be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence.PurchaseOrder;

import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.PurchaseOrderEntity;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.PurchaseOrderLineEntity;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.PurchaseOrderStatusEntity;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence.Repository.PurchaseOrderRepository;
import be.kdg.prog6.warehouseBoundedContext.domain.PurchaseOrder;
import be.kdg.prog6.warehouseBoundedContext.domain.PurchaseOrderLine;
import be.kdg.prog6.warehouseBoundedContext.domain.PurchaseOrderStatus;
import be.kdg.prog6.warehouseBoundedContext.domain.SellerId;
import be.kdg.prog6.warehouseBoundedContext.port.out.PurchaseOrderLoadPort;
import be.kdg.prog6.warehouseBoundedContext.port.out.PurchaseOrderSavePort;
import be.kdg.prog6.warehouseBoundedContext.util.Error.PurchaseOrderDontExistException;
import domain.MaterialType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.IllegalFormatFlagsException;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseOrderDataBaseAdapter implements PurchaseOrderLoadPort, PurchaseOrderSavePort {

    private final PurchaseOrderRepository purchaseOrderRepository;

    // Inject the EntityManager
    @PersistenceContext
    private EntityManager entityManager;

    public PurchaseOrderDataBaseAdapter(PurchaseOrderRepository purchaseOrderRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    @Override
    @Transactional
    public PurchaseOrder save(PurchaseOrder purchaseOrder) {
        PurchaseOrderEntity entity = mapToEntity(purchaseOrder);
        PurchaseOrderEntity savedEntity = entityManager.merge(entity);
        return mapToDomain(savedEntity);
    }

    @Override
    @Transactional
    public PurchaseOrder loadById(UUID purchaseOrderId) {
        return mapToDomain(purchaseOrderRepository.findByPurchaseOrderId(purchaseOrderId));
    }

        @Override
    public List<PurchaseOrder> loadBySellerId(SellerId sellerId) {
        List<PurchaseOrderEntity> entities = purchaseOrderRepository.findBySellerId(sellerId.getSellerID()); // Convert SellerId to UUID
        return entities.stream().map(this::mapToDomain).toList(); // Map entities to domain objects
    }


    @Override
    public List<PurchaseOrder> loadByCustomerName(String customerName) {
        List<PurchaseOrderEntity> entities = purchaseOrderRepository.findByCustomerName(customerName);
        return entities.stream().map(this::mapToDomain).toList();
    }

    @Override
    public List<PurchaseOrder> loadBySellerIdAndMaterialType(SellerId sellerId, MaterialType materialType) {
        List<PurchaseOrderEntity> entities = purchaseOrderRepository.findBySellerIdAndMaterialType(sellerId.getSellerID(), materialType);
        return entities.stream().map(this::mapToDomain).toList();
    }

    @Override
    public List<PurchaseOrder> getAllPurchaseOrdersStatus() {
        return purchaseOrderRepository.getAll().stream().map(this::mapToDomain).toList();
    }


    private PurchaseOrderEntity mapToEntity(PurchaseOrder purchaseOrder) {
        PurchaseOrderEntity entity = new PurchaseOrderEntity(
                purchaseOrder.getPurchaseOrderId(),
                purchaseOrder.getOrderDate(),
                purchaseOrder.getSellerId().getSellerID(),
                purchaseOrder.getCustomerName(),
                null,
                PurchaseOrderStatusEntity.valueOf(purchaseOrder.getStatus().toString()),
                purchaseOrder.getBuyerId()
        );

        List<PurchaseOrderLineEntity> orderLineEntitiesList = purchaseOrder.getOrderLines().stream().map(item -> {
            PurchaseOrderLineEntity orderLine = new PurchaseOrderLineEntity(
                    item.getMaterialType(),
                    item.getQuantity(),
                    item.getPricePerTon()
            );
            orderLine.setPurchaseOrder(entity);
            return orderLine;
        }).toList();

        entity.setPurchaseOrderLines(orderLineEntitiesList);
        return entity;
    }


    private PurchaseOrder mapToDomain(PurchaseOrderEntity entity) {
        List<PurchaseOrderLine> orderLineList = entity.getPurchaseOrderLines().stream().map(item -> {
            return new PurchaseOrderLine(item.getMaterialType(),
                    item.getQuantity(), item.getPricePerTon());
        }).toList();

        return new PurchaseOrder(entity.getOrderDate(), entity.getPurchaseOrderId(),
                new SellerId(entity.getSellerId()), entity.getCustomerName(),
                orderLineList, PurchaseOrderStatus.valueOf(entity.getStatus().toString()) , entity.getBuyerId());
    }
}

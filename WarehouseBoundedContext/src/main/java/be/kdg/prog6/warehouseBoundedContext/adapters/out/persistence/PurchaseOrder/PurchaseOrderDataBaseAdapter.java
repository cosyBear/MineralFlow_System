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
import util.errorClasses.PurchaseOrderDatabaseException;
import util.errorClasses.PurchaseOrderNotFoundException;
import domain.MaterialType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PurchaseOrderDataBaseAdapter implements PurchaseOrderLoadPort, PurchaseOrderSavePort {

    private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseOrderDataBaseAdapter.class);
    private final PurchaseOrderRepository purchaseOrderRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public PurchaseOrderDataBaseAdapter(PurchaseOrderRepository purchaseOrderRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    @Override
    public void save(PurchaseOrder purchaseOrder) {
        try {
            PurchaseOrderEntity existingEntity = purchaseOrderRepository.getPurchaseOrderByPurchaseOrderId(purchaseOrder.getPurchaseOrderId());

            if (existingEntity != null) {
                PurchaseOrderEntity updatedEntity = mapToEntity(purchaseOrder);
                updatedEntity.setPurchaseOrderId(existingEntity.getPurchaseOrderId());
                entityManager.merge(updatedEntity);
            } else {
                PurchaseOrderEntity newEntity = mapToEntity(purchaseOrder);
                entityManager.persist(newEntity);
            }
        } catch (Exception e) {
            LOGGER.error("Failed to save purchase order: {}", purchaseOrder.getPurchaseOrderId(), e);
            throw new PurchaseOrderDatabaseException("Database error: Could not save purchase order", e);
        }
    }

    @Override
    public PurchaseOrder loadById(UUID purchaseOrderId) {
        try {
            PurchaseOrderEntity entity = purchaseOrderRepository.findByPurchaseOrderId(purchaseOrderId);
            if (entity == null) {
                throw new PurchaseOrderNotFoundException("Purchase order not found for ID: " + purchaseOrderId);
            }
            return mapToDomain(entity);
        } catch (Exception e) {
            LOGGER.error("Failed to load purchase order by ID: {}", purchaseOrderId, e);
            throw new PurchaseOrderDatabaseException("Database error: Could not load purchase order by ID", e);
        }
    }

    @Override
    public List<PurchaseOrder> loadBySellerId(SellerId sellerId) {
        try {
            List<PurchaseOrderEntity> entities = purchaseOrderRepository.findBySellerId(sellerId.getSellerID());
            return entities.stream().map(this::mapToDomain).toList();
        } catch (Exception e) {
            LOGGER.error("Failed to load purchase orders by seller ID: {}", sellerId.getSellerID(), e);
            throw new PurchaseOrderDatabaseException("Database error: Could not load purchase orders by seller ID", e);
        }
    }

    @Override
    public List<PurchaseOrder> loadByCustomerName(String customerName) {
        try {
            List<PurchaseOrderEntity> entities = purchaseOrderRepository.findByCustomerName(customerName);
            return entities.stream().map(this::mapToDomain).toList();
        } catch (Exception e) {
            LOGGER.error("Failed to load purchase orders by customer name: {}", customerName, e);
            throw new PurchaseOrderDatabaseException("Database error: Could not load purchase orders by customer name", e);
        }
    }

    @Override
    public List<PurchaseOrder> loadBySellerIdAndMaterialType(SellerId sellerId, MaterialType materialType) {
        try {
            List<PurchaseOrderEntity> entities = purchaseOrderRepository.findBySellerIdAndMaterialType(sellerId.getSellerID(), materialType);
            return entities.stream().map(this::mapToDomain).toList();
        } catch (Exception e) {
            LOGGER.error("Failed to load purchase orders by seller ID: {} and material type: {}", sellerId.getSellerID(), materialType, e);
            throw new PurchaseOrderDatabaseException("Database error: Could not load purchase orders by seller ID and material type", e);
        }
    }

    @Override
    public List<PurchaseOrder> getAllPurchaseOrdersStatus() {
        try {
            return purchaseOrderRepository.getAll().stream().map(this::mapToDomain).toList();
        } catch (Exception e) {
            LOGGER.error("Failed to retrieve all purchase order statuses", e);
            throw new PurchaseOrderDatabaseException("Database error: Could not retrieve all purchase order statuses", e);
        }
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
                orderLineList, PurchaseOrderStatus.valueOf(entity.getStatus().toString()), entity.getBuyerId());
    }
}

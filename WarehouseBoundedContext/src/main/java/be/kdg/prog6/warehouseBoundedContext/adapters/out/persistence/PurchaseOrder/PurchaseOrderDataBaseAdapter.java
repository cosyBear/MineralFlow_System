package be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence.PurchaseOrder;

import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.PurchaseOrderEntity;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.PurchaseOrderLineEntity;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence.Repository.PurchaseOrderRepository;
import be.kdg.prog6.warehouseBoundedContext.domain.PurchaseOrder;
import be.kdg.prog6.warehouseBoundedContext.domain.PurchaseOrderLine;
import be.kdg.prog6.warehouseBoundedContext.domain.SellerId;
import be.kdg.prog6.warehouseBoundedContext.port.out.PurchaseOrderLoadPort;
import be.kdg.prog6.warehouseBoundedContext.port.out.PurchaseOrderSavePort;
import be.kdg.prog6.warehouseBoundedContext.util.Error.PurchaseOrderDontExistException;
import domain.MaterialType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.IllegalFormatFlagsException;
import java.util.List;
import java.util.UUID;

@Service
public class PurchaseOrderDataBaseAdapter implements PurchaseOrderLoadPort, PurchaseOrderSavePort {


    private final PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseOrderDataBaseAdapter(PurchaseOrderRepository purchaseOrderRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
    }


    @Override
    public PurchaseOrder loadById(UUID purchaseOrderId) {
        return purchaseOrderRepository.findById(purchaseOrderId)
                .map(this::mapToDomain)
                .orElseThrow(() -> new PurchaseOrderDontExistException("the purchase order you looking for dont exists Pookie"));
    }

    @Override
    public PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder) {
        PurchaseOrderEntity entity = mapToEntity(purchaseOrder);
        PurchaseOrderEntity savedEntity = purchaseOrderRepository.save(entity);
        return mapToDomain(savedEntity);
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

    private PurchaseOrder mapToDomain(PurchaseOrderEntity entity) {
        List<PurchaseOrderLine> orderLineList = entity.getPurchaseOrderLines().stream().map(item -> {
            return new PurchaseOrderLine(item.getMaterialType(),
                    item.getQuantity(), item.getPricePerTon());
        }).toList();

        return new PurchaseOrder(entity.getOrderDate(), entity.getPurchaseOrderId(),
                new SellerId(entity.getSellerId()), entity.getCustomerName(),
                orderLineList);
    }

    private PurchaseOrderEntity mapToEntity(PurchaseOrder purchaseOrder) {

        PurchaseOrderEntity entity = new PurchaseOrderEntity(purchaseOrder.getOrderDate(),
                purchaseOrder.getSellerId().getSellerID(), purchaseOrder.getCustomerName(), null);

        List<PurchaseOrderLineEntity> orderLineEntitiesList = purchaseOrder.getOrderLines().stream().map(item -> {
            PurchaseOrderLineEntity orderLine = new PurchaseOrderLineEntity(item.getMaterialType(), item.getQuantity(), item.getQuantity());
            orderLine.setPurchaseOrder(entity);
            return orderLine;
        }).toList();

        entity.setPurchaseOrderLines(orderLineEntitiesList);
        return entity;
    }


}

package be.kdg.prog6.watersideboundedcontext.adapters.out.repo;

import be.kdg.prog6.watersideboundedcontext.adapters.out.Entity.ShipmentOrderEntity;
import be.kdg.prog6.watersideboundedcontext.domain.ShipmentOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShipmentOrderRepository extends JpaRepository<ShipmentOrderEntity , UUID> {

    ShipmentOrderEntity findByPurchaseOrder(UUID purchaseOrder);
}

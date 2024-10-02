package be.kdg.prog6.boundedcontextB.adapters.out.persistence.Repository;


import be.kdg.prog6.boundedcontextB.adapters.out.JPAEntity.WarehouseEntity;
import be.kdg.prog6.boundedcontextB.domain.SellerId;
import be.kdg.prog6.boundedcontextB.domain.WarehouseId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WarehouseRepository extends JpaRepository<WarehouseEntity, UUID> {


        Optional<WarehouseEntity> findBySellerId(SellerId sellerId);

        Optional<WarehouseEntity> findByWarehouseNumber(WarehouseId warehouseNumber);





}


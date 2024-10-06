package be.kdg.prog6.landSideBoundedContext.adapters.out.persistence.wareHouse;

import be.kdg.prog6.landSideBoundedContext.adapters.out.entity.WareHouseEntity;
import be.kdg.prog6.landSideBoundedContext.domain.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WareHouseRepository extends JpaRepository<WareHouseEntity , UUID> {

    //to get the list of all the Warehouse for that Seller
    List<WareHouseEntity> findAllBySellerId(UUID sellerId);

    WareHouseEntity findBySellerId(UUID sellerId);

    WareHouseEntity  findBySellerIdAndMaterialType(UUID sellerId , MaterialType materialType);



}

import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import be.kdg.prog6.landSideBoundedContext.domain.Id.WarehouseId;
import be.kdg.prog6.landSideBoundedContext.domain.MaterialType;
import be.kdg.prog6.landSideBoundedContext.domain.WareHouse;
import be.kdg.prog6.landSideBoundedContext.port.out.WarehouseLoadPort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class WarehouseLoadPortStub implements WarehouseLoadPort {

    private WarehouseTestData testData;

    public WarehouseLoadPortStub() {
        testData = WarehouseTestData.defaultData();

    }
    @Override
    public Optional<WareHouse> findById(UUID id) {

        if (testData.warehouseId().warehouseId().equals(id)) {
            return Optional.of(new WareHouse(testData.warehouseId() , testData.sellerId(),  testData.materialType()  , testData.materialAmount()));
        }else
            return Optional.empty();

    }

    @Override
    public List<WareHouse> findAllBySellerId(UUID sellerId) {
        return List.of();
    }

    @Override
    public WareHouse findBySellerId(UUID sellerId) {
        return null;
    }

    @Override
    public WareHouse findBySellerIdAAndMaterialType(UUID sellerId, MaterialType materialType) {
        return null;
    }



    @Override
    public List<WareHouse> warehouseOverview() {
        return List.of();
    }
}

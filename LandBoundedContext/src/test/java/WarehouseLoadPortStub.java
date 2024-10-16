import be.kdg.prog6.landSideBoundedContext.domain.Warehouse;
import be.kdg.prog6.landSideBoundedContext.port.out.WarehouseLoadPort;
import domain.MaterialType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class WarehouseLoadPortStub implements WarehouseLoadPort {

    private WarehouseTestData testData;

    public WarehouseLoadPortStub() {
        testData = WarehouseTestData.defaultData();

    }
    @Override
    public Optional<Warehouse> findById(UUID id) {

        if (testData.warehouseId().warehouseId().equals(id)) {
            return Optional.of(new Warehouse(testData.warehouseId() , testData.sellerId(),  testData.materialType()  , testData.materialAmount()));
        }else
            return Optional.empty();

    }

    @Override
    public List<Warehouse> findAllBySellerId(UUID sellerId) {
        return List.of();
    }

    @Override
    public Warehouse findBySellerId(UUID sellerId) {
        return null;
    }

    @Override
    public Warehouse findBySellerIdAAndMaterialType(UUID sellerId, MaterialType materialType) {
        return null;
    }



    @Override
    public List<Warehouse> warehouseOverview() {
        return List.of();
    }
}

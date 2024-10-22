import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import be.kdg.prog6.landSideBoundedContext.domain.Id.WarehouseId;
import domain.MaterialType;

import java.util.UUID;

public record WarehouseTestData(
        WarehouseId warehouseId,
        SellerId sellerId,
        MaterialType materialType,
        double materialAmount,
        double warehouseExpectedMaterialAmount
) {

    public static WarehouseTestData defaultData() {
        return new WarehouseTestData(
                new WarehouseId(UUID.fromString("c0e9a5a3-bbfd-4e73-bef7-9a2a8bce394b")),
                new SellerId(UUID.fromString("8d50dbe3-68a4-4afc-a242-13818629ac9d")),
                MaterialType.IRON,
                100.0,
                300.0
        );
    }
}

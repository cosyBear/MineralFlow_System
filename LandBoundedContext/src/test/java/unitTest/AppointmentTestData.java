package unitTest;

import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import be.kdg.prog6.landSideBoundedContext.domain.Id.WarehouseId;
import be.kdg.prog6.landSideBoundedContext.domain.LicensePlate;
import domain.MaterialType;

import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentTestData(
        SellerId sellerId,
        WarehouseId warehouseId,
        MaterialType materialType,
        LicensePlate licensePlate,
        double amountOfMaterialTypeInWarehouse,
        LocalDateTime dateTime
) {
    public static AppointmentTestData defaultData() {
        return new AppointmentTestData(
                new SellerId(UUID.fromString("8d50dbe3-68a4-4afc-a242-13818629ac9d")),
                new WarehouseId(UUID.fromString("8d50dbe3-68a4-4afc-a242-13818629ac9f")),
                MaterialType.IRON,
                new LicensePlate("ABC1236"),
                100.0,
                LocalDateTime.parse("2024-10-23T11:00:00")
        );
    }
}
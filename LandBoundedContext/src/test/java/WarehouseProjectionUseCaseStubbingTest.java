import be.kdg.prog6.landSideBoundedContext.core.WarehouseProjectionUseCaseImp;
import be.kdg.prog6.landSideBoundedContext.domain.UpdateWarehouseCommand;
import be.kdg.prog6.landSideBoundedContext.domain.Warehouse;
import be.kdg.prog6.landSideBoundedContext.port.in.WarehouseProjectionUseCase;
import be.kdg.prog6.landSideBoundedContext.port.out.WarehouseSavePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class WarehouseProjectionUseCaseStubbingTest {
    private WarehouseProjectionUseCase sut;
    private WarehouseSavePort warehouseSavePort;


    private WarehouseTestData testData ;

    @BeforeEach
    public void setUp() {
        warehouseSavePort = mock(WarehouseSavePort.class);
        testData = WarehouseTestData.defaultData();  // Use the default test data

        sut = new WarehouseProjectionUseCaseImp(new WarehouseLoadPortStub(), warehouseSavePort);
    }


    @Test
    void shouldUpdateExistingWarehouseIfNotFull() {

        sut.updateWarehouse(new UpdateWarehouseCommand(testData.warehouseId() , 200 , testData.materialType()  ,testData.sellerId()));

        final ArgumentCaptor<Warehouse> warehouseCaptor = ArgumentCaptor.forClass(Warehouse.class);

        verify(warehouseSavePort).Save(warehouseCaptor.capture());


        assertEquals(testData.warehouseId(), warehouseCaptor.getValue().getWarehouseId());
        assertEquals(testData.materialType(), warehouseCaptor.getValue().getMaterialType());
        assertEquals(testData.sellerId(), warehouseCaptor.getValue().getSellerId());
//        assertEquals(testData.warehouseExpectedMaterialAmount(), warehouseCaptor.getValue().getAmountOfMaterial());

    }





}

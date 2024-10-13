//import be.kdg.prog6.landSideBoundedContext.core.weighBridgeUseCaseImp;
//import be.kdg.prog6.landSideBoundedContext.domain.MaterialType;
//import be.kdg.prog6.landSideBoundedContext.domain.weighTruckInCommand;
//import be.kdg.prog6.landSideBoundedContext.port.in.WeighBridgeUseCase;
//import be.kdg.prog6.landSideBoundedContext.port.out.*;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.verify;
//import static org.springframework.http.RequestEntity.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class WeighBridgeControllerIntegrationTest {
//
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    // Mock the external dependencies of the core
//    @MockBean
//    private WeighbridgeTicketLoadPort weighbridgeTicketLoadPort;
//
//    @MockBean
//    private WeighbridgeTicketSavePort weighbridgeTicketSavePort;
//
//    @MockBean
//    private CalendarLoadPort calendarLoadPort;
//
//    @MockBean
//    private CalendarSavePort calendarSavePort;
//
//    @MockBean
//    private WarehouseLoadPort warehouseLoadPort;
//
//    @MockBean
//    private WeighBridgeEventPublisher eventPublisher;
//
//    @Autowired
//    private WeighBridgeUseCase weighBridgeUseCase;
//
//
//    @Test
//    void shouldWeighTruckIn() throws Exception {
//        // Arrange:
//        String requestBody = """
//            {
//              "sellerId": "8d50dbe3-68a4-4afc-a242-13818629ac9d",
//              "licensePlate": "ABC1236",
//              "materialType": "PETCOKE",
//              "time": "2024-10-23T11:00:00",
//              "payload": 100
//            }
//        """;
//
//        // Act:
//        mockMvc.perform(post("/weighbridge/trucks/weighIn")
//                        .contentType(MediaType.APPLICATION_JSON)
//                                .context
//                // Assert: Verify the response status is OK (200)
//                .andExpect(status().isOk());
//
//        // Assert:
//        ArgumentCaptor<weighTruckInCommand> commandCaptor = ArgumentCaptor.forClass(weighTruckInCommand.class);
//        verify(weighBridgeUseCase).weighTruckIn(commandCaptor.capture());
//
//        weighTruckInCommand capturedCommand = commandCaptor.getValue();
//
//        // Assert:
//        assertEquals("ABC1236", capturedCommand.licensePlate().licensePlate());
//        assertEquals(100.0, capturedCommand.startWeight());
//        assertEquals(MaterialType.PETCOKE, capturedCommand.materialType());
//        assertEquals(UUID.fromString("8d50dbe3-68a4-4afc-a242-13818629ac9d"), capturedCommand.sellerId().id());
//        assertEquals(LocalDateTime.parse("2024-10-23T11:00:00"), capturedCommand.weighInTime());
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//}

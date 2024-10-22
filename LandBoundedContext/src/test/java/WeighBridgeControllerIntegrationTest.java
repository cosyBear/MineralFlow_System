import be.kdg.prog6.landSideBoundedContext.LandSideBoundedContextApplication;
import be.kdg.prog6.landSideBoundedContext.adapters.out.WeighBridgeEventPublisherImp;
import be.kdg.prog6.landSideBoundedContext.domain.WeighTruckInCommand;
import be.kdg.prog6.landSideBoundedContext.domain.LicensePlate;
import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import be.kdg.prog6.landSideBoundedContext.port.in.WeighBridgeUseCase;
import be.kdg.prog6.landSideBoundedContext.port.out.WeighbridgeTicketLoadPort;
import domain.MaterialType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = LandSideBoundedContextApplication.class)
public class WeighBridgeControllerIntegrationTest extends AbstractDatabaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WeighBridgeUseCase sut; // Actual service

    @Autowired
    WeighbridgeTicketLoadPort weighbridgeTicketLoadPort; // Actual repository

    @MockBean
    private WeighBridgeEventPublisherImp weighBridgeEventPublisherImp;

    @Test
    void weighTruckInControllerMethod() throws Exception {
        // Arrange:
        String requestBody = """
            {
                "licensePlate": "ABC123M",
                "startWeight": 150.0,
                "materialType": "PETCOKE",
                "sellerId": "8d50dbe3-68a4-4afc-a242-13818629ac9d",
                "weighInTime": "2024-12-23T11:00:00"
            }
        """;

        // Act: Perform the POST request and check for status
        mockMvc.perform(post("/weighbridge/trucks/weighIn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk()); 
    }

    @Test
    public void shouldWeighTruckIn() {
        // Arrange: Prepare a command to invoke the weighTruckIn method
        WeighTruckInCommand command = new WeighTruckInCommand(
                new LicensePlate("ABC123M"),
                150.0,
                MaterialType.PETCOKE,
                new SellerId(UUID.fromString("8d50dbe3-68a4-4afc-a242-13818629ac9d")),
                LocalDateTime.parse("2024-12-23T11:00:00")
        );

        // Act: Call the actual service method (sut)
        sut.weighTruckIn(command);

        // Assert: Verify that the ticket was saved in the repository
        var savedTicket = weighbridgeTicketLoadPort.loadBySellerIDAndMaterialType(command.sellerId().id() , command.materialType());
        assertEquals(savedTicket.getLicensePlate(), command.licensePlate());
        assertEquals(savedTicket.getStartTime(), command.weighInTime());
        assertEquals(savedTicket.getStartWeight(), command.startWeight());
        assertEquals(savedTicket.getEndWeight(), 0);
    }
}

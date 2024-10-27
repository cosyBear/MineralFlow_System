//import be.kdg.prog6.landSideBoundedContext.LandSideBoundedContextApplication;
//import be.kdg.prog6.landSideBoundedContext.adapters.out.WeighBridgeEventPublisherImp;
//import be.kdg.prog6.landSideBoundedContext.core.WeighBridgeUseCaseImpl;
//import be.kdg.prog6.landSideBoundedContext.domain.*;
//import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
//import be.kdg.prog6.landSideBoundedContext.port.in.WeighBridgeUseCase;
//import be.kdg.prog6.landSideBoundedContext.port.in.WeighTruckInCommand;
//import be.kdg.prog6.landSideBoundedContext.port.out.*;
//import domain.MaterialType;
//import jakarta.persistence.EntityManager;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentCaptor;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.mock.mockito.SpyBean;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.lang.reflect.Field;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentCaptor.forClass;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.any;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = LandSideBoundedContextApplication.class)
//@ActiveProfiles("test")
//public class WeighBridgeUseCaseImplIntegrationTest extends AbstractDatabaseTest {
//
//    @Autowired
//    private WeighBridgeUseCase sut;
//
//    @SpyBean
//    private WeighBridgeEventPublisherImp eventPublisher;
//
//    @MockBean
//    private RabbitTemplate rabbitTemplate;
//
//    @Test
//    public void shouldWeighTruckIn() {
//        // Arrange
//        WeighTruckInCommand command = new WeighTruckInCommand(
//                new LicensePlate("ABC1001"),
//                150.0,
//                MaterialType.IRON,
//                new SellerId(UUID.fromString("8d50dbe3-68a4-4afc-a242-13818629ac9d")),
//                LocalDateTime.parse("2024-12-23T11:00:00")
//        );
//
//        // Act
//        sut.weighTruckIn(command);
//
//        // Assert
//        // Verify that publishTruckWeightedIn() was called exactly once
//        ArgumentCaptor<WeighInEvent> eventCaptor = ArgumentCaptor.forClass(WeighInEvent.class);
//        verify(eventPublisher, times(1)).publishTruckWeightedIn(eventCaptor.capture());
//
//        WeighInEvent capturedEvent = eventCaptor.getValue();
//
//        // Assert the properties of the published event
//        assertEquals(command.licensePlate().licensePlate(), capturedEvent.getLicensePlate());
//        assertEquals(command.startWeight(), capturedEvent.getGrossWeight(), 0.01);
//        assertEquals(command.materialType(), capturedEvent.getMaterialType());
//        assertEquals(command.weighInTime(), capturedEvent.getWeighInTime());
//
//        // Verify that rabbitTemplate.convertAndSend() was called exactly once
//        ArgumentCaptor<String> exchangeCaptor = ArgumentCaptor.forClass(String.class);
//        ArgumentCaptor<String> routingKeyCaptor = ArgumentCaptor.forClass(String.class);
//        ArgumentCaptor<Object> messageCaptor = ArgumentCaptor.forClass(Object.class);
//
//        verify(rabbitTemplate, times(1)).convertAndSend(
//                exchangeCaptor.capture(),
//                routingKeyCaptor.capture(),
//                messageCaptor.capture()
//        );
//
//        // Verify exchange, routing key, and message
//        assertEquals("weighbridgeExchange", exchangeCaptor.getValue());
//        String expectedRoutingKey = "truck." + command.licensePlate().licensePlate() + ".in";
//        assertEquals(expectedRoutingKey, routingKeyCaptor.getValue());
//        assertEquals(capturedEvent, messageCaptor.getValue());
//    }
//
//    @Test
//    public void testEventPublisherIsSameInstance() throws Exception {
//        Field field = WeighBridgeUseCaseImpl.class.getDeclaredField("eventPublisher");
//        field.setAccessible(true);
//        Object injectedEventPublisher = field.get(sut);
//        assertSame(eventPublisher, injectedEventPublisher, "The eventPublisher in WeighBridgeUseCaseImpl should be the spy bean");
//    }
//}

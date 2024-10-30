package integrationTestContainers;

import be.kdg.prog6.landSideBoundedContext.LandSideBoundedContextApplication;
import be.kdg.prog6.landSideBoundedContext.core.WeighBridgeUseCaseImpl;
import be.kdg.prog6.landSideBoundedContext.domain.*;
import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import be.kdg.prog6.landSideBoundedContext.port.in.WeighTruckInCommand;
import be.kdg.prog6.landSideBoundedContext.port.out.CalendarLoadPort;
import be.kdg.prog6.landSideBoundedContext.port.out.CalendarSavePort;
import be.kdg.prog6.landSideBoundedContext.port.out.WeighBridgeEventPublisher;
import be.kdg.prog6.landSideBoundedContext.port.out.WeighbridgeTicketLoadPort;
import be.kdg.prog6.landSideBoundedContext.port.out.WeighbridgeTicketSavePort;
import domain.MaterialType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.DirtiesContext;
import util.errorClasses.AppointmentCompletedException;
import util.errorClasses.LicensePlateDontMatchException;
import util.errorClasses.TruckLateException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@SpringBootTest(classes = LandSideBoundedContextApplication.class)
@Profile("test")
public class WeighBridgeUseCaseImplIntegrationTest extends AbstractDatabaseTest {


    @Autowired
    private CalendarLoadPort calendarLoadPort;

    @Autowired
    private CalendarSavePort calendarSavePort;

    @Autowired
    private WeighbridgeTicketLoadPort weighbridgeTicketLoadPort;

    @Autowired
    private WeighbridgeTicketSavePort weighbridgeTicketSavePort;

    @MockBean
    private WeighBridgeEventPublisher eventPublisher;

    @Autowired
    private WeighBridgeUseCaseImpl sut;


    private DayCalendar testDayCalendar;
    private SellerId sellerId;
    private SellerId sellerId2;
    private LicensePlate licensePlate;
    private LicensePlate licensePlate2;
    private MaterialType materialType;

    @BeforeEach
    void setup() {
        sellerId = new SellerId(UUID.randomUUID());
        sellerId2 = new SellerId(UUID.randomUUID());
        licensePlate = new LicensePlate("ABC123");
        licensePlate2 = new LicensePlate("FailTest");
        materialType = MaterialType.IRON;

        testDayCalendar = new DayCalendar(LocalDateTime.now().toLocalDate(), List.of(
                new Appointment(materialType, LocalDateTime.now().plusHours(1), sellerId, licensePlate, AppointmentStatus.AWAITING_ARRIVAL),
                new Appointment(materialType, LocalDateTime.parse("2024-10-19T11:00:00"), sellerId2, licensePlate2, AppointmentStatus.Completed)

        ));

        calendarSavePort.saveDayCalendar(testDayCalendar);
    }


    @Test
    void testWeighTruckIn() {
        //arrange
        double startWeight = 1000.0;
        WeighTruckInCommand weighInCommand = new WeighTruckInCommand(
                licensePlate, startWeight, materialType, sellerId, LocalDateTime.now()
        );

        //act
        sut.weighTruckIn(weighInCommand);

        // Verify
        verify(eventPublisher, times(1)).publishTruckWeightedIn(any(WeighInEvent.class));

        DayCalendar updatedCalendar = calendarLoadPort.loadAppointmentsByDate(weighInCommand.weighInTime().toLocalDate());


        // assert
        Appointment appointment = updatedCalendar.getAppointments().stream()
                .filter(app -> app.getLicensePlate().equals(licensePlate))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Appointment should exist for the given license plate."));
        assertEquals(AppointmentStatus.ON_SITE, appointment.getStatus(), "Appointment status should be updated to ON_SITE.");
    }

    @Test
    void testWeighTruckInAppointmentAlreadyCompletedShouldFail() {
        // Arrange
        double startWeight = 1000.0;
        LocalDateTime appointmentTime = LocalDateTime.parse("2024-10-19T11:00:00");

        WeighTruckInCommand weighInCommand = new WeighTruckInCommand(
                licensePlate2, startWeight, materialType, sellerId2, appointmentTime
        );

        // Act & Assert
        assertThrows(AppointmentCompletedException.class, () -> sut.weighTruckIn(weighInCommand));

        // Verify
        verify(eventPublisher, never()).publishTruckWeightedIn(any(WeighInEvent.class));

        // Verify
        DayCalendar updatedCalendar = calendarLoadPort.loadAppointmentsByDate(appointmentTime.toLocalDate());
        Appointment appointment = updatedCalendar.getAppointments().stream()
                .filter(app -> app.getLicensePlate().equals(licensePlate2))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Appointment should exist for the given license plate."));
        assertEquals(AppointmentStatus.Completed, appointment.getStatus(), "Appointment status should remain COMPLETED and not be reused.");
    }


}

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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@Transactional
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
    private LicensePlate licensePlate;
    private MaterialType materialType;

    @BeforeEach
    void setup() {
        sellerId = new SellerId(UUID.randomUUID());
        licensePlate = new LicensePlate("ABC123");
        materialType = MaterialType.IRON;

        testDayCalendar = new DayCalendar(LocalDateTime.now().toLocalDate(), List.of(
                new Appointment(materialType, LocalDateTime.now().plusHours(1), sellerId, licensePlate, AppointmentStatus.AWAITING_ARRIVAL)
        ));

        calendarSavePort.saveDayCalendar(testDayCalendar);
    }

    @Test
    void testWeighTruckIn() {
        double startWeight = 1000.0;
        WeighTruckInCommand weighInCommand = new WeighTruckInCommand(
                licensePlate, startWeight, materialType, sellerId, LocalDateTime.now()
        );
        sut.weighTruckIn(weighInCommand);

        // Verify that the calendar has been updated
        DayCalendar updatedCalendar = calendarLoadPort.loadAppointmentsByDate(weighInCommand.weighInTime().toLocalDate());

        // Verify that the event was published
        verify(eventPublisher, times(1)).publishTruckWeightedIn(any(WeighInEvent.class));

        // Check that the appointment status is updated
        Appointment appointment = updatedCalendar.getAppointments().stream()
                .filter(app -> app.getLicensePlate().equals(licensePlate))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Appointment should exist for the given license plate."));
        assertEquals(AppointmentStatus.ON_SITE, appointment.getStatus(), "Appointment status should be updated to ON_SITE.");
    }

    @Test
    void testWeighTruckInOutsideArrivalWindow() {
        double startWeight = 1200.0;
        LocalDateTime outsideArrivalTime = LocalDateTime.now().plusHours(3); // Outside the expected window
        WeighTruckInCommand weighInCommand = new WeighTruckInCommand(
                licensePlate, startWeight, materialType, sellerId, outsideArrivalTime
        );

        sut.weighTruckIn(weighInCommand);

        // Load the updated calendar and verify that the appointment status has not changed to ON_SITE
        DayCalendar updatedCalendar = calendarLoadPort.loadAppointmentsByDate(outsideArrivalTime.toLocalDate());

        // Ensure no event is published since the truck arrived outside the scheduled window
        verify(eventPublisher, never()).publishTruckWeightedIn(any(WeighInEvent.class));

        // Check that the appointment status remains AWAITING_ARRIVAL
        Appointment appointment = updatedCalendar.getAppointments().stream()
                .filter(app -> app.getLicensePlate().equals(licensePlate))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Appointment should exist for the given license plate."));
        assertEquals(AppointmentStatus.AWAITING_ARRIVAL, appointment.getStatus(), "Appointment status should remain AWAITING_ARRIVAL for outside window arrival.");
    }


}

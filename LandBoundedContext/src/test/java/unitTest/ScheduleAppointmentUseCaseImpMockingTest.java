package unitTest;

import be.kdg.prog6.landSideBoundedContext.core.ScheduleAppointmentUseCaseImp;
import be.kdg.prog6.landSideBoundedContext.domain.*;
import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import be.kdg.prog6.landSideBoundedContext.port.in.ScheduleAppointmentCommand;
import be.kdg.prog6.landSideBoundedContext.port.in.ScheduleAppointmentUseCase;
import be.kdg.prog6.landSideBoundedContext.port.out.CalendarLoadPort;
import be.kdg.prog6.landSideBoundedContext.port.out.CalendarSavePort;
import be.kdg.prog6.landSideBoundedContext.port.out.WarehouseLoadPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import util.errorClasses.WarehouseIsFullException;

import java.util.ArrayList;


import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
public class ScheduleAppointmentUseCaseImpMockingTest {

    private ScheduleAppointmentUseCase sut;
    private CalendarLoadPort calendarLoadPort;
    private WarehouseLoadPort warehouseLoadPort;
    private CalendarSavePort calendarSavePort;
    private AppointmentTestData appointmentTestData;

    @BeforeEach
    public void setUp() {
        appointmentTestData = AppointmentTestData.defaultData();
        calendarLoadPort = mock(CalendarLoadPort.class);
        warehouseLoadPort = mock(WarehouseLoadPort.class);
        calendarSavePort = mock(CalendarSavePort.class);

        when(warehouseLoadPort.findBySellerIdAAndMaterialType(
                new SellerId(appointmentTestData.sellerId().id()),
                appointmentTestData.materialType()
        )).thenReturn(createWarehouse());

        when(calendarLoadPort.loadAppointmentsByDate(
                appointmentTestData.dateTime().toLocalDate())
        ).thenReturn(createCalendar());

        sut = new ScheduleAppointmentUseCaseImp(calendarLoadPort, warehouseLoadPort, calendarSavePort);
    }

    private Warehouse createWarehouse() {
        return new Warehouse(
                appointmentTestData.warehouseId(),
                appointmentTestData.sellerId(),
                appointmentTestData.materialType(),
                appointmentTestData.amountOfMaterialTypeInWarehouse()
        );
    }

    private DayCalendar createCalendar() {
        return new DayCalendar(appointmentTestData.dateTime().toLocalDate(), new ArrayList<>());
    }

    @Test
    void shouldScheduleAppointmentSuccessfully() {
        // Arrange
        ScheduleAppointmentCommand command = new ScheduleAppointmentCommand(
                appointmentTestData.licensePlate(),
                appointmentTestData.materialType(),
                appointmentTestData.dateTime(),
                appointmentTestData.sellerId()
        );

        // Act
        Appointment result = sut.scheduleAppointment(command);

        // Capture
        ArgumentCaptor<DayCalendar> calendarCaptor = ArgumentCaptor.forClass(DayCalendar.class);
        verify(calendarSavePort).saveDayCalendar(calendarCaptor.capture());

        // Assert
        DayCalendar capturedCalendar = calendarCaptor.getValue();
        assertEquals(appointmentTestData.dateTime().toLocalDate(), capturedCalendar.getDate());
        assertEquals(1, capturedCalendar.getAppointments().size());

        assertEquals(appointmentTestData.materialType(), result.getMaterialType());
        assertEquals(appointmentTestData.dateTime(), result.getTime());
        assertEquals(appointmentTestData.sellerId(), result.getSellerId());
        assertEquals(appointmentTestData.licensePlate().licensePlate(), result.getLicensePlate().licensePlate());
    }


    @Test
    void shouldThrowWarehouseIsFullExceptionWhenWarehouseIsFull() {
        // Arrange:
        double amountOfMaterial = 500.0 * 0.8; // make it "full"
        Warehouse fullWarehouse = new Warehouse(
                appointmentTestData.warehouseId(),
                appointmentTestData.sellerId(),
                appointmentTestData.materialType(),
                amountOfMaterial
        );

        when(warehouseLoadPort.findBySellerIdAAndMaterialType(
                appointmentTestData.sellerId(),
                appointmentTestData.materialType()
        )).thenReturn(fullWarehouse);

        ScheduleAppointmentCommand command = new ScheduleAppointmentCommand(
                appointmentTestData.licensePlate(),
                appointmentTestData.materialType(),
                appointmentTestData.dateTime(),
                appointmentTestData.sellerId()
        );

        // Act & Assert: Expect a WarehouseIsFullException to be thrown
        assertThrows(WarehouseIsFullException.class, () -> sut.scheduleAppointment(command));
    }


}

//import be.kdg.prog6.landSideBoundedContext.core.ScheduleAppointmentUseCaseImp;
//import be.kdg.prog6.landSideBoundedContext.domain.*;
//import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
//import be.kdg.prog6.landSideBoundedContext.domain.Id.WarehouseId;
//import be.kdg.prog6.landSideBoundedContext.port.in.ScheduleAppointmentUseCase;
//import be.kdg.prog6.landSideBoundedContext.port.out.AppointmentSavePort;
//import be.kdg.prog6.landSideBoundedContext.port.out.CalendarLoadPort;
//import be.kdg.prog6.landSideBoundedContext.port.out.WarehouseLoadPort;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//@AutoConfigureMockMvc
//public class ScheduleAppointmentUseCaseImpMockingTest {
//
//
//    private ScheduleAppointmentUseCase sut;
//    private  CalendarLoadPort calendarLoadPort;
//    private  AppointmentSavePort appointmentSavePort;
//    private  WarehouseLoadPort warehouseLoadPort;
//    private static final SellerId sellerID =  new SellerId(UUID.fromString("8d50dbe3-68a4-4afc-a242-13818629ac9d"));
//    private static final WarehouseId WarehouseId =  new WarehouseId(UUID.fromString("8d50dbe3-68a4-4afc-a242-13818629ac9f"));
//    private static final MaterialType materialType = MaterialType.IRON;
//    private static final LicensePlate licensePlate =  new LicensePlate("ABC1236");
//    private static final double  amountOfMaterialTypeInWarehouse = 100;
//    private static final LocalDateTime dateTime = LocalDateTime.parse("2024-10-23T11:00:00");
//
//    @BeforeEach
//    public void setUp() {
//
//        calendarLoadPort =  mock(CalendarLoadPort.class);
//        appointmentSavePort =  mock(AppointmentSavePort.class);
//        warehouseLoadPort =  mock(WarehouseLoadPort.class);
//
//        when(warehouseLoadPort.findBySellerIdAAndMaterialType(sellerID.id() , materialType)).thenReturn(
//                        createWarehouse());
//        when(calendarLoadPort.loadAppointmentsByDate(dateTime.toLocalDate())).thenReturn(
//                createCalendar()
//        );
//        sut = new ScheduleAppointmentUseCaseImp(appointmentSavePort , calendarLoadPort , warehouseLoadPort);
//
//
//    }
//    public WareHouse createWarehouse() {
//        return  new WareHouse(WarehouseId  , sellerID , materialType , amountOfMaterialTypeInWarehouse);
//    }
//    public DayCalendar createCalendar() {
//        List<Appointment> appointments = new ArrayList<>();
//        Appointment appointment1 = new Appointment(
//                materialType,
//                dateTime,
//                sellerID,
//                licensePlate,
//                150,
//                AppointmentStatus.AWAITING_ARRIVAL
//        );
//        appointments.add(appointment1);
//        return new DayCalendar(dateTime.toLocalDate(), appointments);
//    }
//
//    @Test
//    void shouldScheduleAppointmentSuccessfully() {
//
//        ScheduleAppointmentCommand command = new ScheduleAppointmentCommand(
//                licensePlate,
//                materialType,
//                dateTime,
//                sellerID,
//                150
//        );
//        // Act
//        sut.scheduleAppointment(command);
//
//        ArgumentCaptor<Appointment> appointmentCaptor = ArgumentCaptor.forClass(Appointment.class);
//
//        //verify
//        verify(appointmentSavePort).saveAppointment(appointmentCaptor.capture());
//
//        //Assert
//        Appointment capturedAppointment = appointmentCaptor.getValue();
//        assertEquals(materialType, capturedAppointment.getMaterialType());
//        assertEquals(dateTime, capturedAppointment.getTime());
//        assertEquals(sellerID, capturedAppointment.getSellerId());
//        assertEquals(licensePlate.licensePlate(), capturedAppointment.getLicensePlate().licensePlate());
//        assertEquals(150, capturedAppointment.getPayload());
//
//    }
//
//
//
//
//
//}

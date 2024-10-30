package be.kdg.prog6.watersideboundedcontext.adapters.out.Entity;

import be.kdg.prog6.watersideboundedcontext.domain.ShipmentOrderStatus;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Table(name = "shipment_order", catalog = "water_db")
@Entity
public class ShipmentOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID shipmentOrderId;

    private UUID purchaseOrder;

    private LocalDateTime expectedArrivalTime;
    private LocalDateTime expectedDepartureTime;
    private UUID vesselId;

    @Nullable
    private LocalDateTime actualArrivalTime;

    @Nullable
    private LocalDateTime actualDepartureTime;

    @Embedded
    @Nullable
    private InspectionOperationEntity inspectionOperation;

    @Embedded
    @Nullable
    private BunkeringOperationEntity bunkeringOperation;
    @Enumerated(EnumType.STRING)
    private ShipmentOrderStatus status;


    public ShipmentOrderEntity() {
    }

    public ShipmentOrderEntity(UUID purchaseOrder, LocalDateTime expectedArrivalTime, LocalDateTime expectedDepartureTime, UUID vesselId,
                               InspectionOperationEntity inspectionOperation, BunkeringOperationEntity bunkeringOperation,
                               UUID shipmentOrderId, LocalDateTime actualArrivalTime, LocalDateTime actualDepartureTime , ShipmentOrderStatus status) {
        this.purchaseOrder = purchaseOrder;
        this.expectedArrivalTime = expectedArrivalTime;
        this.expectedDepartureTime = expectedDepartureTime;
        this.vesselId = vesselId;
        this.inspectionOperation = inspectionOperation;
        this.bunkeringOperation = bunkeringOperation;
        this.shipmentOrderId = shipmentOrderId;
        this.actualArrivalTime = actualArrivalTime;
        this.actualDepartureTime = actualDepartureTime;
        this.status = status;
    }
}

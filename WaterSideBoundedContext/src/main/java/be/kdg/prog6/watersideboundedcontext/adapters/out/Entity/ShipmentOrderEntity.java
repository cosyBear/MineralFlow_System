package be.kdg.prog6.watersideboundedcontext.adapters.out.Entity;

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
    private UUID purchaseOrder;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private UUID vesselId;

    @Embedded
    @Nullable
    private InspectionOperationEntity inspectionOperation;

    @Embedded
    @Nullable
    private BunkeringOperationEntity bunkeringOperation;

    public ShipmentOrderEntity() {

    }

    public ShipmentOrderEntity(UUID purchaseOrder, LocalDateTime arrivalTime, LocalDateTime departureTime, UUID vesselId, InspectionOperationEntity inspectionOperation, BunkeringOperationEntity bunkeringOperation) {
        this.purchaseOrder = purchaseOrder;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.vesselId = vesselId;
        this.inspectionOperation = inspectionOperation;
        this.bunkeringOperation = bunkeringOperation;
    }

    public ShipmentOrderEntity(UUID purchaseOrder, LocalDateTime arrivalTime, LocalDateTime departureTime, UUID vesselId) {
        this.purchaseOrder = purchaseOrder;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.vesselId = vesselId;

    }
}

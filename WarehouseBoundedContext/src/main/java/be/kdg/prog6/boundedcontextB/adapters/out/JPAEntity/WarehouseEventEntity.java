package be.kdg.prog6.boundedcontextB.adapters.out.JPAEntity;

import be.kdg.prog6.boundedcontextB.domain.EventType;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(catalog = "warehouse_db", name = "WarehouseEventEntity")
public class WarehouseEventEntity {

    @JdbcTypeCode(SqlTypes.CHAR)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)// Store UUID as CHAR(36) in the database
    private UUID wareHouseEventId;

    @Column(name = "TimeOfEvent")
    private LocalDateTime time;

    @Column(nullable = false , name = "typeOfEvent")
    @Enumerated(EnumType.STRING)
    private EventType type;

    @Column(nullable = true , name = "amountOfMaterial")
    private double amount;

    @JdbcTypeCode(SqlTypes.CHAR)  // Store UUID as CHAR(36) in the database
    @Column(name = "weighBridgeTicketId" ,nullable = false)
    private UUID weighBridgeTicketId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouseEventsWindowEntityId")
    private WarehouseEventsWindowEntity warehouseEventsWindow;


    public WarehouseEventEntity() {

    }

    public WarehouseEventEntity(UUID wareHouseEventId, LocalDateTime time, EventType type, double amount, UUID weighBridgeTicketId, WarehouseEventsWindowEntity warehouseEventsWindow) {
        this.wareHouseEventId = wareHouseEventId;
        this.time = time;
        this.type = type;
        this.amount = amount;
        this.weighBridgeTicketId = weighBridgeTicketId;
        this.warehouseEventsWindow = warehouseEventsWindow;
    }


    public UUID getWareHouseEventId() {
        return wareHouseEventId;
    }

    public void setWareHouseEventId(UUID id) {
        this.wareHouseEventId = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public UUID getWeighBridgeTicketId() {
        return weighBridgeTicketId;
    }

    public void setWeighBridgeTicketId(UUID weighBridgeTicketId) {
        this.weighBridgeTicketId = weighBridgeTicketId;
    }

    public WarehouseEventsWindowEntity getWarehouseEventsWindow() {
        return warehouseEventsWindow;
    }

    public void setWarehouseEventsWindow(WarehouseEventsWindowEntity warehouseEventsWindow) {
        this.warehouseEventsWindow = warehouseEventsWindow;
    }
}

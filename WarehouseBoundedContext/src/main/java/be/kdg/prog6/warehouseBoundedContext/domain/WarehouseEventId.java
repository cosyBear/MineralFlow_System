package be.kdg.prog6.warehouseBoundedContext.domain;
import java.util.Objects;
import java.util.UUID;
public class WarehouseEventId {
    private UUID id;

    public WarehouseEventId(UUID id) {
        this.id = id;
    }

    public WarehouseEventId() {
        this(UUID.randomUUID());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        WarehouseEventId that = (WarehouseEventId) obj;
        return Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "WarehouseEventId{id=" + id + '}';
    }
}

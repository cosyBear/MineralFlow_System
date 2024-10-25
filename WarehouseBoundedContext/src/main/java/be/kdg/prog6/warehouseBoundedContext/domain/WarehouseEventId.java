package be.kdg.prog6.warehouseBoundedContext.domain;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;
@Setter
@Getter
public class WarehouseEventId {
    private UUID id;

    public WarehouseEventId(UUID id) {
        this.id = id;
    }

    public WarehouseEventId() {
        this(UUID.randomUUID());
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

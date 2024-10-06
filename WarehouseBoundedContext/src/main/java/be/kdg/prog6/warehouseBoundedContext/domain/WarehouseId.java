package be.kdg.prog6.warehouseBoundedContext.domain;
import java.util.UUID;
public class WarehouseId {
    private UUID id;

    public WarehouseId(UUID id) {
        this.id = id;
    }

    public WarehouseId() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "WarehouseId{" + "id=" + id + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        WarehouseId that = (WarehouseId) obj;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

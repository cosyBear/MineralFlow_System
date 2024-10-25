package be.kdg.prog6.landSideBoundedContext.port.out;

import domain.MaterialType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
public class TruckOnSiteQuery {

    private MaterialType materialType;
    private LocalDateTime time;
    private UUID sellerId;
    private String licensePlate;

    public TruckOnSiteQuery() {

    }

    public TruckOnSiteQuery(MaterialType materialType, LocalDateTime time, UUID sellerId, String licensePlate) {
        this.materialType = materialType;
        this.time = time;
        this.sellerId = sellerId;
        this.licensePlate = licensePlate;
    }

    @Override
    public String toString() {
        return "AppointmentQuery[" +
                "materialType=" + materialType + ", " +
                "time=" + time + ", " +
                "sellerId=" + sellerId + ", " +
                "licensePlate=" + licensePlate;
    }
}

package be.kdg.prog6.landSideBoundedContext.domain;

import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import be.kdg.prog6.landSideBoundedContext.domain.Id.WeighBridgeTicketId;
import domain.MaterialType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class WeighbridgeTicket {

    private WeighBridgeTicketId weighBridgeTicketId;

    private LicensePlate licensePlate;

    private SellerId sellerId;

    private double startWeight;

    private double endWeight;

    private LocalDateTime endTime;
    private LocalDateTime startTime;

    private MaterialType materialType;

    public WeighbridgeTicket(){

    }

    public WeighbridgeTicket(WeighBridgeTicketId weighBridgeTicketId, LicensePlate licensePlate, SellerId sellerId, double startWeight, double endWeight, MaterialType materialType, LocalDateTime startTime) {

        this.weighBridgeTicketId = weighBridgeTicketId;
        this.licensePlate = licensePlate;
        this.sellerId = sellerId;
        this.startWeight = startWeight;
        this.endWeight = endWeight;
        this.materialType = materialType;
        this.startTime = startTime;

    }


    public void truckWeighsOut(LocalDateTime endTime , double endWeight){
        this.endTime = endTime;
        this.endWeight = endWeight;
    }



}

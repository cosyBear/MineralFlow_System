package be.kdg.prog6.landSideBoundedContext.port.in;

import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import be.kdg.prog6.landSideBoundedContext.domain.LicensePlate;
import domain.MaterialType;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

public record WeighTruckInCommand(
        @NotNull LicensePlate licensePlate,
         double startWeight,
        @NotNull MaterialType materialType,
        @NotNull SellerId sellerId,
        @NotNull LocalDateTime weighInTime) {

}

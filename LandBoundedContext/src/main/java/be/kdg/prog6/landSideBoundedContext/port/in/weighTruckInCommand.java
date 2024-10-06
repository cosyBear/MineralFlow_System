package be.kdg.prog6.landSideBoundedContext.port.in;

import be.kdg.prog6.landSideBoundedContext.domain.LicensePlate;
import be.kdg.prog6.landSideBoundedContext.domain.MaterialType;
import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

public record weighTruckInCommand(
        @NotNull LicensePlate licensePlate,
         double startWeight,
        @NotNull MaterialType materialType,
        @NotNull SellerId sellerId,
        @NotNull LocalDateTime weighInTime) {

}

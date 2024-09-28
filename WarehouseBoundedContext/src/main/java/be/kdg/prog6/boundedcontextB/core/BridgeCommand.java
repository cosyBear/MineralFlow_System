package be.kdg.prog6.boundedcontextB.core;

import be.kdg.prog6.boundedcontextB.domain.LicensePlate;
import be.kdg.prog6.boundedcontextB.domain.MaterialType;

public record BridgeCommand(LicensePlate licensePlate , MaterialType materialType){
}

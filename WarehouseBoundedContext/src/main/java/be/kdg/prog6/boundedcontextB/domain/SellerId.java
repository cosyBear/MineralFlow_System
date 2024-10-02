package be.kdg.prog6.boundedcontextB.domain;

import jakarta.persistence.Embeddable;

import java.util.UUID;
@Embeddable
public record SellerId(UUID sellerID) {
}

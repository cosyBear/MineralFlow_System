package be.kdg.prog6.warehouseBoundedContext.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;

import java.util.Objects;
import java.util.UUID;

@Embeddable
public final class SellerId {
    private  UUID sellerID;

    public SellerId(UUID sellerID) {
        if (sellerID == null) {
            throw new IllegalArgumentException("Seller ID cannot be null");
        }
        this.sellerID = sellerID;
    }

    public SellerId() {

    }

    public UUID getSellerID() {
        return sellerID;
    }

    public UUID sellerID() {
        return sellerID;
    }

    public void setSellerID(UUID sellerID) {
        this.sellerID = sellerID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (SellerId) obj;
        return Objects.equals(this.sellerID, that.sellerID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sellerID);
    }

    @Override
    public String toString() {
        return "SellerId[" +
                "sellerID=" + sellerID + ']';
    }

}
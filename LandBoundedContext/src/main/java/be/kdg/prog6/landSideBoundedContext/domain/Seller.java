package be.kdg.prog6.landSideBoundedContext.domain;

import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.UUID;


public class Seller {
    private SellerId id;
    private String companyName;

    public Seller() {
    }


    @JsonCreator
    public static SellerId fromString(String id) {
        return new SellerId(UUID.fromString(id));
    }

    @JsonValue
    public String toString() {
        return id.toString();
    }

    public void setId(SellerId id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}

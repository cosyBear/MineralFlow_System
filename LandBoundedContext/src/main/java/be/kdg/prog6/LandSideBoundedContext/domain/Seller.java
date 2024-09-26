package be.kdg.prog6.LandSideBoundedContext.domain;

import java.time.LocalDate;

public class Seller {
    private SellerId id;
    private String companyName;

    public Seller(SellerId id, String companyName) {
        this.id = id;
        this.companyName = companyName;
    }
}

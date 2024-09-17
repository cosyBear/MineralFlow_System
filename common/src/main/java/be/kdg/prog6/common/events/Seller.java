package be.kdg.prog6.common.events;

import java.time.LocalDate;

public class Seller {
    private String companyName;
    private Integer sellerId;
    private LocalDate date;
    private double payLoad;
    private String MaterialType;
    private Integer time;


    public Seller(String companyName, Integer sellerId, LocalDate date, double payLoad, String materialType, Integer time) {
        this.companyName = companyName;
        this.sellerId = sellerId;
        this.date = date;
        this.payLoad = payLoad;
        MaterialType = materialType;
        this.time = time;
    }


}

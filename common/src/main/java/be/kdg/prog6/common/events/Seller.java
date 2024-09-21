package be.kdg.prog6.common.events;

import java.time.LocalDate;

public class Seller {
    private String companyName;
    private LocalDate date;
    private double payLoad;
    private String MaterialType;
    private Integer time;


    public Seller(String companyName, LocalDate date, double payLoad, String materialType, Integer time) {
        this.companyName = companyName;
        this.date = date;
        this.payLoad = payLoad;
        MaterialType = materialType;
        this.time = time;
    }


}

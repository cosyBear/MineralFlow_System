package be.kdg.prog6.LandSideBoundedContext.adapters.out.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "trucks")
public class TruckEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licenseNumber;
    private double payload;

    public TruckEntity() {}

    public TruckEntity(String licenseNumber, double payload) {
        this.licenseNumber = licenseNumber;
        this.payload = payload;
    }

    public Long getId() {
        return id;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public double getPayload() {
        return payload;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void setPayload(double payload) {
        this.payload = payload;
    }

}

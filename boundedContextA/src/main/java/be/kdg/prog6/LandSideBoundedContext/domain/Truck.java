package be.kdg.prog6.LandSideBoundedContext.domain;

public class Truck {
    private String licenseNumber;
    private double payload;


    public Truck() {
    }

    public Truck(String licenseNumber, double payload) {
        this.licenseNumber = licenseNumber;
        this.payload = payload;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public double getPayload() {
        return payload;
    }

    public void setPayload(double payload) {
        this.payload = payload;
    }
}

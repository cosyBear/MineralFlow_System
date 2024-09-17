package be.kdg.prog6.LandSideBoundedContext.Core;

public class Truck {
    private String licenseNumber;
    private double payload;


    public Truck(String licenseNumber, double payload) {
        this.licenseNumber = licenseNumber;
        this.payload = payload;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public double getPayload() {
        return payload;
    }

    public void setPayload(double payload) {
        this.payload = payload;
    }
}

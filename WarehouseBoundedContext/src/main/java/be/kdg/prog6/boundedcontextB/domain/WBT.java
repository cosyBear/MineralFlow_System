package be.kdg.prog6.boundedcontextB.domain;

import java.time.LocalDateTime;

public class WBT {

    private LicensePlate licensePlate;
    private LocalDateTime timeOfWeighing;
    private double weights;


    public WBT(LicensePlate licensePlate, LocalDateTime timeOfWeighing, double weights) {
        this.licensePlate = licensePlate;
        this.timeOfWeighing = timeOfWeighing;
        this.weights = weights;
    }


    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(LicensePlate licensePlate) {
        this.licensePlate = licensePlate;
    }

    public LocalDateTime getTimeOfWeighing() {
        return timeOfWeighing;
    }

    public void setTimeOfWeighing(LocalDateTime timeOfWeighing) {
        this.timeOfWeighing = timeOfWeighing;
    }

    public double getWeights() {
        return weights;
    }

    public void setWeights(double weights) {
        this.weights = weights;
    }
}

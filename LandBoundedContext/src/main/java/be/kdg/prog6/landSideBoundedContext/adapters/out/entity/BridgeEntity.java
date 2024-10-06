package be.kdg.prog6.landSideBoundedContext.adapters.out.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(catalog = "app_db")
public class BridgeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String bridgeId;
    private double currentTruckWeight;
    private String currentTruckLicensePlate;
    private String assignedWarehouse;
    private LocalDateTime timeIn;
    private LocalDateTime timeOut;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBridgeId() {
        return bridgeId;
    }

    public void setBridgeId(String bridgeId) {
        this.bridgeId = bridgeId;
    }

    public double getCurrentTruckWeight() {
        return currentTruckWeight;
    }

    public void setCurrentTruckWeight(double currentTruckWeight) {
        this.currentTruckWeight = currentTruckWeight;
    }

    public String getCurrentTruckLicensePlate() {
        return currentTruckLicensePlate;
    }

    public void setCurrentTruckLicensePlate(String currentTruckLicensePlate) {
        this.currentTruckLicensePlate = currentTruckLicensePlate;
    }

    public String getAssignedWarehouse() {
        return assignedWarehouse;
    }

    public void setAssignedWarehouse(String assignedWarehouse) {
        this.assignedWarehouse = assignedWarehouse;
    }

    public LocalDateTime getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(LocalDateTime timeIn) {
        this.timeIn = timeIn;
    }

    public LocalDateTime getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(LocalDateTime timeOut) {
        this.timeOut = timeOut;
    }

}

package be.kdg.prog6.boundedcontextB.domain;

public class Truck {
    private LicensePlate licensePlate;
    private MaterialType materialType;
    private Integer wareHouseNumber;
    private Double payload;


    public Truck(LicensePlate licensePlate,MaterialType materialType, double payload ) {

        this.licensePlate = licensePlate;
        this.materialType = materialType;
        this.payload= payload;

    }


    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(LicensePlate licensePlate) {
        this.licensePlate = licensePlate;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public Integer getWareHouseNumber() {
        return wareHouseNumber;
    }

    public void setWareHouseNumber(Integer wareHouseNumber) {
        this.wareHouseNumber = wareHouseNumber;
    }

    public Double getPayload() {
        return payload;
    }

    public void setPayload(Double payload) {
        this.payload = payload;
    }

}

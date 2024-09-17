package be.kdg.prog6.LandSideBoundedContext.Core;

public class Seller {
    private String companyName;

    public Seller(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    // A truck should only exist in the system when a Seller makes an appointment.
    public Appointment makeAppointment(TimeSlot timeSlot, String licensePlate, double payload, MaterialType materialType) {
        Truck truck = new Truck(licensePlate, payload);
        return new Appointment(timeSlot, truck, materialType);
    }
}

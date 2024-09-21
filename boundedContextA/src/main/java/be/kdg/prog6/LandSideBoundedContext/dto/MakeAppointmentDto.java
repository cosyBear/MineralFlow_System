package be.kdg.prog6.LandSideBoundedContext.dto;

public record MakeAppointmentDto(
        String companyName,
        String licensePlate,
        double Payload,
        String materialType ,
        String date,
        Integer earliestArrivalTime,
        Integer latestArrivalTime

) {
}

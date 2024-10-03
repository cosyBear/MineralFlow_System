package be.kdg.prog6.landSideBoundedContext.adapters.out.persistence.WeighbridgeTicket;

import be.kdg.prog6.landSideBoundedContext.adapters.out.entity.WeighbridgeTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface WeighbridgeTicketEntityRepository extends JpaRepository<WeighbridgeTicketEntity , UUID>
{

    @Query("SELECT w FROM WeighbridgeTicketEntity w WHERE w.weighBridgeTicketId = :id")
    WeighbridgeTicketEntity loadById(UUID id);


}

package be.kdg.prog6.LandSideBoundedContext.adapters.out.persistence.WeighbridgeTicket;

import be.kdg.prog6.LandSideBoundedContext.adapters.out.entity.WeighbridgeTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WeighbridgeTicketEntityRepository extends JpaRepository<WeighbridgeTicketEntity , UUID>
{

    WeighbridgeTicketEntity loadById(UUID id);


}

package be.kdg.prog6.landSideBoundedContext.adapters.out.persistence.WeighbridgeTicket;

import be.kdg.prog6.landSideBoundedContext.adapters.out.entity.WeighbridgeTicketEntity;
import be.kdg.prog6.landSideBoundedContext.domain.WeighbridgeTicket;
import be.kdg.prog6.landSideBoundedContext.port.out.WeighbridgeTicketLoadPort;
import be.kdg.prog6.landSideBoundedContext.port.out.WeighbridgeTicketSavePort;
import be.kdg.prog6.landSideBoundedContext.util.errorClasses.WeighbridgeTicketNotFound;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WeighbridgeTicketDataBaseAdapter implements WeighbridgeTicketLoadPort, WeighbridgeTicketSavePort {

    private final WeighbridgeTicketEntityRepository weighbridgeTicketEntityRepository;
    private final ModelMapper modelMapper;

    public WeighbridgeTicketDataBaseAdapter(WeighbridgeTicketEntityRepository weighbridgeTicketEntityRepository, ModelMapper modelMapper) {
        this.weighbridgeTicketEntityRepository = weighbridgeTicketEntityRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public WeighbridgeTicket loadById(UUID id)  {

        WeighbridgeTicketEntity weighbridgeTicketEntity = weighbridgeTicketEntityRepository.findById(id)
                .orElseThrow(() -> new WeighbridgeTicketNotFound("The WeighbridgeTicket is not found"));
        return modelMapper.map(weighbridgeTicketEntity, WeighbridgeTicket.class);
    }

    @Override
    public void save(WeighbridgeTicket ticket) {
        weighbridgeTicketEntityRepository.save(modelMapper.map(ticket, WeighbridgeTicketEntity.class));

    }
}

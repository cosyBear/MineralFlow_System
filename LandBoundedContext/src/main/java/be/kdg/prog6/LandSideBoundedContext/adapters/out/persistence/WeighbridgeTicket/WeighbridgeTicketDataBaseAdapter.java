package be.kdg.prog6.LandSideBoundedContext.adapters.out.persistence.WeighbridgeTicket;

import be.kdg.prog6.LandSideBoundedContext.adapters.out.entity.WeighbridgeTicketEntity;
import be.kdg.prog6.LandSideBoundedContext.domain.WeighbridgeTicket;
import be.kdg.prog6.LandSideBoundedContext.port.out.WeighbridgeTicketLoadPort;
import be.kdg.prog6.LandSideBoundedContext.port.out.WeighbridgeTicketSavePort;
import be.kdg.prog6.LandSideBoundedContext.util.errorClasses.WeighbridgeTicketNotFound;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
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
    public WeighbridgeTicket loadById(UUID id) throws WeighbridgeTicketNotFound {

        WeighbridgeTicketEntity weighbridgeTicketEntity = weighbridgeTicketEntityRepository.findById(id)
                .orElseThrow(() -> new WeighbridgeTicketNotFound("The WeighbridgeTicket is not found"));
        return modelMapper.map(weighbridgeTicketEntity, WeighbridgeTicket.class);
    }

    @Override
    public void saveWeighbridgeTicket(WeighbridgeTicket weighbridgeTicket) {

        UUID ticketId = weighbridgeTicket.getWeighBridgeTicketId().id();

        Optional<WeighbridgeTicketEntity> existingEntityOpt = weighbridgeTicketEntityRepository.findById(ticketId);

        WeighbridgeTicketEntity weighbridgeTicketEntity;

        if (existingEntityOpt.isPresent()) {
            weighbridgeTicketEntity = existingEntityOpt.get();

            modelMapper.map(weighbridgeTicket, weighbridgeTicketEntity);
        } else {
            weighbridgeTicketEntity = modelMapper.map(weighbridgeTicket, WeighbridgeTicketEntity.class);
        }

        weighbridgeTicketEntityRepository.save(weighbridgeTicketEntity);

    }
}

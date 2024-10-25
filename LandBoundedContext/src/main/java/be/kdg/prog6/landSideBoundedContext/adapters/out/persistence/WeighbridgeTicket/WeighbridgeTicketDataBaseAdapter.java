package be.kdg.prog6.landSideBoundedContext.adapters.out.persistence.WeighbridgeTicket;

import be.kdg.prog6.landSideBoundedContext.adapters.out.entity.WeighbridgeTicketEntity;
import be.kdg.prog6.landSideBoundedContext.domain.WeighbridgeTicket;
import be.kdg.prog6.landSideBoundedContext.port.out.WeighbridgeTicketLoadPort;
import be.kdg.prog6.landSideBoundedContext.port.out.WeighbridgeTicketSavePort;
import util.errorClasses.DatabaseOperationException;
import util.errorClasses.WeighbridgeTicketNotFound;
import domain.MaterialType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class WeighbridgeTicketDataBaseAdapter implements WeighbridgeTicketLoadPort, WeighbridgeTicketSavePort {

    private final WeighbridgeTicketEntityRepository weighbridgeTicketEntityRepository;
    private final ModelMapper modelMapper;

    public WeighbridgeTicketDataBaseAdapter(WeighbridgeTicketEntityRepository weighbridgeTicketEntityRepository, @Qualifier("landModelMapper") ModelMapper modelMapper) {
        this.weighbridgeTicketEntityRepository = weighbridgeTicketEntityRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public WeighbridgeTicket loadById(UUID id) {

        WeighbridgeTicketEntity weighbridgeTicketEntity = weighbridgeTicketEntityRepository.findById(id)
                .orElseThrow(() -> new WeighbridgeTicketNotFound("The WeighbridgeTicket is not found"));
        return modelMapper.map(weighbridgeTicketEntity, WeighbridgeTicket.class);
    }

    @Override
    public void save(WeighbridgeTicket ticket) {
        try {
            weighbridgeTicketEntityRepository.save(modelMapper.map(ticket, WeighbridgeTicketEntity.class));
        } catch (Exception e) {
            throw new DatabaseOperationException("Database error: Could not save weighbridge ticket" +  e);
        }
    }

    @Override
    public WeighbridgeTicket loadBySellerIDAndMaterialType(UUID id, MaterialType materialType) {
        try {
            WeighbridgeTicketEntity weighbridgeTicketEntity = weighbridgeTicketEntityRepository.loadBySellerIdAndMaterialType(id, materialType);
            if (weighbridgeTicketEntity == null) {
                throw new WeighbridgeTicketNotFound("The WeighbridgeTicket is not found for seller ID: " + id + " and material type: " + materialType);
            }
            return modelMapper.map(weighbridgeTicketEntity, WeighbridgeTicket.class);
        } catch (Exception e) {
            throw new WeighbridgeTicketNotFound("Database error: Could not load weighbridge ticket for seller ID " + id + " and material type " + materialType +  e);
        }
    }


}

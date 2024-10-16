package be.kdg.prog6.landSideBoundedContext.adapters.out.persistence.WeighbridgeTicket;

import be.kdg.prog6.landSideBoundedContext.adapters.out.entity.WeighbridgeTicketEntity;
import be.kdg.prog6.landSideBoundedContext.domain.WeighbridgeTicket;
import be.kdg.prog6.landSideBoundedContext.port.out.WeighbridgeTicketLoadPort;
import be.kdg.prog6.landSideBoundedContext.port.out.WeighbridgeTicketSavePort;
import be.kdg.prog6.landSideBoundedContext.util.errorClasses.WeighbridgeTicketNotFound;
import domain.MaterialType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class WeighbridgeTicketDataBaseAdapter implements WeighbridgeTicketLoadPort, WeighbridgeTicketSavePort {

    private final WeighbridgeTicketEntityRepository weighbridgeTicketEntityRepository;
    private final ModelMapper modelMapper;

    public WeighbridgeTicketDataBaseAdapter(WeighbridgeTicketEntityRepository weighbridgeTicketEntityRepository, @Qualifier("landModelMapper")ModelMapper modelMapper) {
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



    @Override
    public WeighbridgeTicket loadBySellerIDAndMaterialType(UUID id , MaterialType materialType){

        WeighbridgeTicketEntity weighbridgeTicketEntity = weighbridgeTicketEntityRepository.loadBySellerIdAndMaterialType(id, materialType);

        return modelMapper.map(weighbridgeTicketEntity, WeighbridgeTicket.class);


    }






}

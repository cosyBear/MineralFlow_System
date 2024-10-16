package be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence;

import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEventEntity;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.persistence.Repository.WarehouseEventEntityRepository;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseEvent;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseEventId;
import be.kdg.prog6.warehouseBoundedContext.port.out.WarehouseEvent.WarehouseEventLoadPort;
import be.kdg.prog6.warehouseBoundedContext.port.out.WarehouseEvent.WarehouseEventSavePort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Component
public class WarehouseEventDataBaseAdapter implements WarehouseEventSavePort, WarehouseEventLoadPort {

    private final WarehouseEventEntityRepository warehouseEventEntityRepository;
    private final ModelMapper modelMapper;

    public WarehouseEventDataBaseAdapter(WarehouseEventEntityRepository warehouseEventEntityRepository,@Qualifier("warehouse") ModelMapper modelMapper) {
        this.warehouseEventEntityRepository = warehouseEventEntityRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<WarehouseEvent> findAllEvents() {
        List<WarehouseEventEntity> warehouseEventEntityList =  warehouseEventEntityRepository.findAllEvents();

        List<WarehouseEvent> warehouseEventList = new ArrayList<>();

        warehouseEventEntityList.forEach(warehouseEventEntity -> {
            warehouseEventList.add(this.modelMapper.map(warehouseEventEntity, WarehouseEvent.class));
        });

        return warehouseEventList;
    }

    @Override
    public void save(WarehouseEvent warehouseEvent) {

        warehouseEventEntityRepository.save(this.modelMapper.map(warehouseEvent, WarehouseEventEntity.class));


    }
}

package be.kdg.prog6.warehouseBoundedContext.util.Mapper;

import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEventEntity;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = UuidMapper.class , componentModel = "spring")
public interface WarehouseEventMapper {

    @Mapping(source = "wareHouseEventId", target = "id", qualifiedByName = "uuidToWarehouseEventId")
    @Mapping(source = "weighBridgeTicketId", target = "WeighBridgeTicketId")
    WarehouseEvent toDomain(WarehouseEventEntity entity);

    @Mapping(source = "id", target = "wareHouseEventId", qualifiedByName = "warehouseEventIdToUuid")
    @Mapping(source = "WeighBridgeTicketId", target = "weighBridgeTicketId")
    WarehouseEventEntity toEntity(WarehouseEvent domain);
}

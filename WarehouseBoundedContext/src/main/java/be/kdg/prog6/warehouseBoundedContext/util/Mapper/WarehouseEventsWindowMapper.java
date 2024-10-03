package be.kdg.prog6.warehouseBoundedContext.util.Mapper;

import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEventsWindowEntity;
import be.kdg.prog6.warehouseBoundedContext.domain.WarehouseEventsWindow;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {WarehouseEventMapper.class, UuidMapper.class})
public interface WarehouseEventsWindowMapper {

    @Mapping(source = "warehouseEventWindowId", target = "warehouseEventsWindowId")
    @Mapping(source = "warehouseId", target = "warehouseId", qualifiedByName = "uuidToWarehouseId")
    @Mapping(source = "warehouseEventList", target = "warehouseEventList")
    WarehouseEventsWindow toDomain(WarehouseEventsWindowEntity entity);

    @Mapping(source = "warehouseEventsWindowId", target = "warehouseEventWindowId")
    @Mapping(source = "warehouseId", target = "warehouseId", qualifiedByName = "warehouseIdToUuid")
    @Mapping(source = "warehouseEventList", target = "warehouseEventList")
    WarehouseEventsWindowEntity toEntity(WarehouseEventsWindow domain);
}

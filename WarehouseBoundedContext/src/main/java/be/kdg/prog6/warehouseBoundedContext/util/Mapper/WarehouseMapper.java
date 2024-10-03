package be.kdg.prog6.warehouseBoundedContext.util.Mapper;

// WarehouseMapper.java

import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEntity;
import be.kdg.prog6.warehouseBoundedContext.domain.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {WarehouseEventsWindowMapper.class, UuidMapper.class})
public interface WarehouseMapper {

    @Mapping(source = "warehouseNumber", target = "warehouseNumber", qualifiedByName = "uuidToWarehouseId")
    @Mapping(source = "sellerId", target = "sellerId")
    @Mapping(source = "warehouseEventsWindowEntity", target = "eventsWindow")
    Warehouse toDomain(WarehouseEntity entity);

    @Mapping(source = "warehouseNumber", target = "warehouseNumber", qualifiedByName = "warehouseIdToUuid")
    @Mapping(source = "sellerId", target = "sellerId")
    @Mapping(source = "eventsWindow", target = "warehouseEventsWindowEntity")
    WarehouseEntity toEntity(Warehouse domain);
}

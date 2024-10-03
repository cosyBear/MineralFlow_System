package be.kdg.prog6.warehouseBoundedContext.util;

import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEntity;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEventEntity;
import be.kdg.prog6.warehouseBoundedContext.adapters.out.jpaEntity.WarehouseEventsWindowEntity;
import be.kdg.prog6.warehouseBoundedContext.domain.*;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.TypeMap;


@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Set matching strategy to ensure strict mapping
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // Define Converters for custom types
        // UUID <-> WarehouseId
        Converter<UUID, WarehouseId> uuidToWarehouseIdConverter = context ->
                context.getSource() == null ? null : new WarehouseId(context.getSource());
        Converter<WarehouseId, UUID> warehouseIdToUUIDConverter = context ->
                context.getSource() == null ? null : context.getSource().id();

        // UUID <-> WarehouseEventId
        Converter<UUID, WarehouseEventId> uuidToWarehouseEventIdConverter = context ->
                context.getSource() == null ? null : new WarehouseEventId(context.getSource());
        Converter<WarehouseEventId, UUID> warehouseEventIdToUUIDConverter = context ->
                context.getSource() == null ? null : context.getSource().id();

        // UUID <-> SellerId
        Converter<UUID, SellerId> uuidToSellerIdConverter = context ->
                context.getSource() == null ? null : new SellerId(context.getSource());
        Converter<SellerId, UUID> sellerIdToUUIDConverter = context ->
                context.getSource() == null ? null : context.getSource().sellerID();

        // Converters for WarehouseEventsWindow
        Converter<WarehouseEventsWindow, WarehouseEventsWindowEntity> windowDomainToEntityConverter = context -> {
            WarehouseEventsWindow source = context.getSource();
            if (source == null) {
                return null;
            }
            WarehouseEventsWindowEntity entity = new WarehouseEventsWindowEntity();
            entity.setWarehouseEventWindowId(source.getWarehouseEventsWindowId());
            entity.setWarehouseId(source.getWarehouseId().id());

            List<WarehouseEventEntity> eventEntities = source.getWarehouseEventList().stream()
                    .map(event -> modelMapper.map(event, WarehouseEventEntity.class))
                    .collect(Collectors.toList());

            // Set back-reference to the parent window
            for (WarehouseEventEntity eventEntity : eventEntities) {
                eventEntity.setWarehouseEventsWindow(entity);
            }
            entity.setWarehouseEventList(eventEntities);

            return entity;
        };

        Converter<WarehouseEventsWindowEntity, WarehouseEventsWindow> windowEntityToDomainConverter = context -> {
            WarehouseEventsWindowEntity source = context.getSource();
            if (source == null) {
                return null;
            }
            WarehouseEventsWindow domain = new WarehouseEventsWindow();
            domain.setWarehouseEventsWindowId(source.getWarehouseEventWindowId());
            domain.setWarehouseId(new WarehouseId(source.getWarehouseId()));

            List<WarehouseEvent> events = source.getWarehouseEventList().stream()
                    .map(eventEntity -> modelMapper.map(eventEntity, WarehouseEvent.class))
                    .collect(Collectors.toList());
            domain.setWarehouseEventList(events);

            return domain;
        };

        // Define PropertyMaps for Warehouse <-> WarehouseEntity
        PropertyMap<Warehouse, WarehouseEntity> warehouseDomainToEntityMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                using(warehouseIdToUUIDConverter).map(source.getWarehouseNumber()).setWarehouseNumber(null);
                map(source.getMaterialType(), destination.getMaterialType());
                map(source.getSellerId(), destination.getSellerId());  // Map SellerId directly
                using(windowDomainToEntityConverter).map(source.getEventsWindow(), destination.getWarehouseEventsWindowEntity());
            }
        };

        PropertyMap<WarehouseEntity, Warehouse> warehouseEntityToDomainMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                using(uuidToWarehouseIdConverter).map(source.getWarehouseNumber()).setWarehouseNumber(null);
                map(source.getMaterialType(), destination.getMaterialType());
                map(source.getSellerId(), destination.getSellerId());  // Map SellerId directly
                using(windowEntityToDomainConverter).map(source.getWarehouseEventsWindowEntity(), destination.getEventsWindow());
            }
        };

        // TypeMap with Provider for WarehouseEventEntity -> WarehouseEvent (for records)
        TypeMap<WarehouseEventEntity, WarehouseEvent> eventTypeMap = modelMapper.createTypeMap(WarehouseEventEntity.class, WarehouseEvent.class);
        eventTypeMap.setProvider(request -> {
            WarehouseEventEntity source = (WarehouseEventEntity) request.getSource();
            return new WarehouseEvent(
                    new WarehouseEventId(source.getWareHouseEventId()),
                    source.getTime(),
                    source.getType(),
                    source.getAmount(),
                    source.getWeighBridgeTicketId()
            );
        });

        // TypeMap with Provider for WarehouseEvent -> WarehouseEventEntity
        TypeMap<WarehouseEvent, WarehouseEventEntity> eventEntityTypeMap = modelMapper.createTypeMap(WarehouseEvent.class, WarehouseEventEntity.class);
        eventEntityTypeMap.setProvider(request -> {
            WarehouseEvent source = (WarehouseEvent) request.getSource();
            WarehouseEventEntity entity = new WarehouseEventEntity();
            entity.setWareHouseEventId(source.id().id());
            entity.setTime(source.time());
            entity.setType(source.type());
            entity.setAmount(source.materialTrueWeight());
            entity.setWeighBridgeTicketId(source.WeighBridgeTicketId());
            return entity;
        });

        // Add mappings and converters to ModelMapper
        modelMapper.addMappings(warehouseDomainToEntityMap);
        modelMapper.addMappings(warehouseEntityToDomainMap);
        modelMapper.addConverter(windowDomainToEntityConverter);
        modelMapper.addConverter(windowEntityToDomainConverter);
        modelMapper.addConverter(uuidToSellerIdConverter);
        modelMapper.addConverter(sellerIdToUUIDConverter);
        modelMapper.addConverter(uuidToWarehouseIdConverter);
        modelMapper.addConverter(warehouseIdToUUIDConverter);
        modelMapper.addConverter(uuidToWarehouseEventIdConverter);
        modelMapper.addConverter(warehouseEventIdToUUIDConverter);

        return modelMapper;
    }
}

//
//@Configuration
//public class ModelMapperConfig {
//
//    @Bean
//    public ModelMapper modelMapper() {
//        ModelMapper modelMapper = new ModelMapper();
//
//        // Converters for SellerId and UUID
//        final Converter<UUID, SellerId> uuidToSellerIdConverter = context -> new SellerId(context.getSource());
//        final Converter<SellerId, UUID> sellerIdToUUIDConverter = context -> context.getSource().getSellerID();
//
//        // Converters for WarehouseId and UUID
//        final Converter<UUID, WarehouseId> uuidToWarehouseIdConverter = context -> new WarehouseId(context.getSource());
//        final Converter<WarehouseId, UUID> warehouseIdToUUIDConverter = context -> context.getSource().id();
//
//        // Converters for WarehouseEventId and UUID
//        final Converter<UUID, WarehouseEventId> uuidToWarehouseEventIdConverter = context -> new WarehouseEventId(context.getSource());
//        final Converter<WarehouseEventId, UUID> warehouseEventIdToUUIDConverter = context -> context.getSource().id();
//
//        // Converter for WarehouseEventsWindow to WarehouseEventsWindowEntity
//        final Converter<WarehouseEventsWindow, WarehouseEventsWindowEntity> windowDomainToEntityConverter = context -> {
//            WarehouseEventsWindow source = context.getSource();
//            WarehouseEventsWindowEntity destination = new WarehouseEventsWindowEntity(source.getWarehouseId().id());
//            List<WarehouseEventEntity> eventEntities = source.getWarehouseEventList().stream()
//                    .map(event -> modelMapper.map(event, WarehouseEventEntity.class))
//                    .collect(Collectors.toList());
//            destination.setWarehouseEventList(eventEntities);
//            return destination;
//        };
//
//        // Converter for WarehouseEventsWindowEntity to WarehouseEventsWindow
//        final Converter<WarehouseEventsWindowEntity, WarehouseEventsWindow> windowEntityToDomainConverter = context -> {
//            WarehouseEventsWindowEntity source = context.getSource();
//            WarehouseEventsWindow destination = new WarehouseEventsWindow();
//            destination.setWarehouseEventsWindowId(source.getWarehouseEventWindowId());
//            destination.setWarehouseId(new WarehouseId(source.getWarehouseId()));
//            List<WarehouseEvent> events = source.getWarehouseEventList().stream()
//                    .map(eventEntity -> modelMapper.map(eventEntity, WarehouseEvent.class))
//                    .collect(Collectors.toList());
//            destination.setWarehouseEventList(events);
//            return destination;
//        };
//
//// Remove the converter for SellerId to UUID in the mapping
//        PropertyMap<Warehouse, WarehouseEntity> warehouseDomainToEntityMap = new PropertyMap<>() {
//            @Override
//            protected void configure() {
//                using(warehouseIdToUUIDConverter).map(source.getWarehouseNumber()).setWarehouseNumber(null);
//                map(source.getMaterialType(), destination.getMaterialType());
//                map(source.getSellerId(), destination.getSellerId());  // Map SellerId directly
//                using(windowDomainToEntityConverter).map(source.getEventsWindow(), destination.getWarehouseEventsWindowEntity());
//            }
//        };
//
//// Similarly, adjust the reverse mapping
//        PropertyMap<WarehouseEntity, Warehouse> warehouseEntityToDomainMap = new PropertyMap<>() {
//            @Override
//            protected void configure() {
//                using(uuidToWarehouseIdConverter).map(source.getWarehouseNumber()).setWarehouseNumber(null);
//                map(source.getMaterialType(), destination.getMaterialType());
//                map(source.getSellerId(), destination.getSellerId());  // Map SellerId directly
//                using(windowEntityToDomainConverter).map(source.getWarehouseEventsWindowEntity(), destination.getEventsWindow());
//            }
//        };
//        Converter<WarehouseEventEntity, WarehouseEvent> eventEntityToDomainConverter = context -> {
//            WarehouseEventEntity source = context.getSource();
//            return new WarehouseEvent(
//                    new WarehouseEventId(source.getWareHouseEventId()),
//                    source.getTime(),
//                    source.getType(),
//                    source.getAmount(),
//                    source.getWeighBridgeTicketId()
//            );
//        };
//
//
//        // Converter for WarehouseEventEntity to WarehouseEvent
//        Converter<WarehouseEvent, WarehouseEventEntity> eventDomainToEntityConverter = context -> {
//            WarehouseEvent source = context.getSource();
//            WarehouseEventEntity destination = new WarehouseEventEntity();
//            destination.setWareHouseEventId(source.id().id());
//            destination.setTime(source.time());
//            destination.setType(source.type());
//            destination.setAmount(source.materialTrueWeight());
//            destination.setWeighBridgeTicketId(source.WeighBridgeTicketId());
//            return destination;
//        };
//
//
//        // Add mappings
//        modelMapper.addMappings(warehouseDomainToEntityMap);
//        modelMapper.addMappings(warehouseEntityToDomainMap);
//        modelMapper.addConverter(windowDomainToEntityConverter);
//        modelMapper.addConverter(windowEntityToDomainConverter);
//        modelMapper.addConverter(eventDomainToEntityConverter);
//        modelMapper.addConverter(eventEntityToDomainConverter);
//
//        modelMapper.addConverter(uuidToSellerIdConverter);
//        modelMapper.addConverter(sellerIdToUUIDConverter);
//        modelMapper.addConverter(uuidToWarehouseIdConverter);
//        modelMapper.addConverter(warehouseIdToUUIDConverter);
//        modelMapper.addConverter(uuidToWarehouseEventIdConverter);
//        modelMapper.addConverter(warehouseEventIdToUUIDConverter);
//
//        return modelMapper;
//    }
//}

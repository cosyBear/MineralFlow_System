//package be.kdg.prog6.boundedcontextB.util;
//
//import be.kdg.prog6.boundedcontextB.adapters.out.JPAEntity.WarehouseEntity;
//import be.kdg.prog6.boundedcontextB.adapters.out.JPAEntity.WarehouseEventEntity;
//import be.kdg.prog6.boundedcontextB.adapters.out.JPAEntity.WarehouseEventsWindowEntity;
//import be.kdg.prog6.boundedcontextB.domain.Warehouse;
//import be.kdg.prog6.boundedcontextB.domain.WarehouseEvent;
//import be.kdg.prog6.boundedcontextB.domain.WarehouseEventsWindow;
//import org.modelmapper.ModelMapper;
//import org.modelmapper.PropertyMap;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import java.util.stream.Collectors;
//
//
//
//import be.kdg.prog6.boundedcontextB.adapters.out.JPAEntity.*;
//import be.kdg.prog6.boundedcontextB.domain.*;
//import org.modelmapper.Converter;
//import org.modelmapper.ModelMapper;
//import org.modelmapper.PropertyMap;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.UUID;
//import java.util.stream.Collectors;
//
//@Configuration
//public class ModelMapperConfig {
//
//    @Bean
//    public ModelMapper modelMapper() {
//        ModelMapper modelMapper = new ModelMapper();
//
//        // Map WarehouseEntity to Warehouse domain object
//        PropertyMap<WarehouseEntity, Warehouse> warehouseEntityToDomainMap = new PropertyMap<>() {
//            @Override
//            protected void configure() {
//                map(source.getWarehouseNumber(), destination.getWarehouseNumber());
//                map(source.getMaterialType(), destination.getMaterialType());
//                map(source.getSellerId(), destination.getSellerId());
//                skip(destination.getEventsWindow()); // Will be handled in a separate converter
//            }
//        };
//
//        // Map Warehouse domain object to WarehouseEntity
//        PropertyMap<Warehouse, WarehouseEntity> warehouseDomainToEntityMap = new PropertyMap<>() {
//            @Override
//            protected void configure() {
//                map(source.getWarehouseNumber(), destination.getWarehouseNumber());
//                map(source.getMaterialType(), destination.getMaterialType());
//                map(source.getSellerId(), destination.getSellerId());
//                skip(destination.getWarehouseEventsWindowEntity()); // Will be handled in a separate converter
//            }
//        };
//
//        // Converter for WarehouseEventsWindowEntity to WarehouseEventsWindow
//        Converter<WarehouseEventsWindowEntity, WarehouseEventsWindow> windowEntityToDomainConverter = context -> {
//            WarehouseEventsWindowEntity source = context.getSource();
//            WarehouseEventsWindow destination = new WarehouseEventsWindow();
//            destination.setWarehouseEventsWindowId(source.getWarehouseEventWindowId());
//            destination.setWarehouseId(new WarehouseId(source.getWarehouseId()));
//            destination.setWarehouseEventList(source.getWarehouseEventList().stream()
//                    .map(eventEntity -> modelMapper.map(eventEntity, WarehouseEvent.class))
//                    .collect(Collectors.toList()));
//            return destination;
//        };
//
//        // Converter for WarehouseEventsWindow to WarehouseEventsWindowEntity
//        Converter<WarehouseEventsWindow, WarehouseEventsWindowEntity> windowDomainToEntityConverter = context -> {
//            WarehouseEventsWindow source = context.getSource();
//            WarehouseEventsWindowEntity destination = new WarehouseEventsWindowEntity(source.getWarehouseId().id());
//            destination.setWarehouseEventList(source.getWarehouseEventList().stream()
//                    .map(event -> modelMapper.map(event, WarehouseEventEntity.class))
//                    .collect(Collectors.toList()));
//            return destination;
//        };
//
//        // Converter for WarehouseEventEntity to WarehouseEvent
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
//        // Converter for WarehouseEvent to WarehouseEventEntity
//        Converter<WarehouseEvent, WarehouseEventEntity> eventDomainToEntityConverter = context -> {
//            WarehouseEvent source = context.getSource();
//            return new WarehouseEventEntity(
//                    source.id().id(),
//                    source.time(),
//                    source.type(),
//                    source.amount(),
//                    source.WeighBridgeTicketId(),
//                    null // Will be linked to WarehouseEventsWindowEntity later
//            );
//        };
//
//        // Add mappings and converters
//        modelMapper.addMappings(warehouseEntityToDomainMap);
//        modelMapper.addMappings(warehouseDomainToEntityMap);
//        modelMapper.addConverter(windowEntityToDomainConverter);
//        modelMapper.addConverter(windowDomainToEntityConverter);
//        modelMapper.addConverter(eventEntityToDomainConverter);
//        modelMapper.addConverter(eventDomainToEntityConverter);
//
//        return modelMapper;
//    }
//}
//

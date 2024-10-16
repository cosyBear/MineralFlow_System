package be.kdg.prog6.landSideBoundedContext.util.config;


import be.kdg.prog6.landSideBoundedContext.adapters.out.entity.AppointmentEntity;
import be.kdg.prog6.landSideBoundedContext.adapters.out.entity.WareHouseEntity;
import be.kdg.prog6.landSideBoundedContext.adapters.out.entity.WeighbridgeTicketEntity;
import be.kdg.prog6.landSideBoundedContext.domain.*;
import be.kdg.prog6.landSideBoundedContext.domain.Id.WarehouseId;
import be.kdg.prog6.landSideBoundedContext.domain.Id.WeighBridgeTicketId;
import be.kdg.prog6.landSideBoundedContext.domain.Id.SellerId;
import be.kdg.prog6.landSideBoundedContext.domain.AppointmentQuery;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);


        Converter<UUID, SellerId> uuidToSellerIdConverter = new Converter<>() {
            @Override
            public SellerId convert(MappingContext<UUID, SellerId> context) {
                return new SellerId(context.getSource());
            }
        };
        modelMapper.addConverter(new Converter<WeighBridgeTicketId, UUID>() {
            @Override
            public UUID convert(MappingContext<WeighBridgeTicketId, UUID> context) {
                return context.getSource().id(); // assuming you have a method id() that returns the UUID in WeighBridgeTicketId
            }
        });


        Converter<SellerId, UUID> sellerIdToUUIDConverter = new Converter<>() {
            @Override
            public UUID convert(MappingContext<SellerId, UUID> context) {
                return context.getSource().id();
            }
        };

        Converter<String, LicensePlate> stringToLicensePlateConverter = new Converter<>() {
            @Override
            public LicensePlate convert(MappingContext<String, LicensePlate> context) {
                return new LicensePlate(context.getSource());
            }
        };

        Converter<LicensePlate, String> licensePlateToStringConverter = new Converter<>() {
            @Override
            public String convert(MappingContext<LicensePlate, String> context) {
                return context.getSource().licensePlate();
            }
        };


        Converter<UUID, WarehouseId> uuidToWarehouseIdConverter = new Converter<>() {
            @Override
            public WarehouseId convert(MappingContext<UUID, WarehouseId> context) {
                return new WarehouseId(context.getSource());
            }
        };

        Converter<WarehouseId, UUID> warehouseIdToUUIDConverter = new Converter<>() {
            @Override
            public UUID convert(MappingContext<WarehouseId, UUID> context) {
                return context.getSource().warehouseId();  // Assuming WarehouseId has a method id() that returns UUID
            }
        };

        Converter<UUID, WeighBridgeTicketId> uuidToWeighBridgeTicketId = new Converter<>() {
            @Override
            public WeighBridgeTicketId convert(MappingContext<UUID, WeighBridgeTicketId> context) {
                return new WeighBridgeTicketId(context.getSource());
            }
        };

        Converter<WeighBridgeTicketId, UUID> WeighBridgeTicketIdToUUIDConverter = new Converter<>() {
            @Override
            public UUID convert(MappingContext<WeighBridgeTicketId, UUID> context) {
                return context.getSource().id();  // Assuming WarehouseId has a method id() that returns UUID
            }
        };



        modelMapper.addConverter(uuidToWarehouseIdConverter);
        modelMapper.addConverter(warehouseIdToUUIDConverter);





        modelMapper.addConverter(uuidToWeighBridgeTicketId);
        modelMapper.addConverter(WeighBridgeTicketIdToUUIDConverter);




        modelMapper.addMappings(new PropertyMap<WeighbridgeTicket, WeighbridgeTicketEntity>() {
            @Override
            protected void configure() {
                map(source.getWeighBridgeTicketId(), destination.getWeighBridgeTicketId());
                map(source.getLicensePlate(), destination.getLicensePlate());
                map(source.getSellerId(), destination.getSellerId());
                map(source.getStartWeight(), destination.getStartWeight());
                map(source.getEndWeight(), destination.getEndWeight());
                map(source.getStartTime() , destination.getStartTime());
                map(source.getEndTime() , destination.getEndTime());
                map(source.getMaterialType() , destination.getMaterialType());
            }
        });

        modelMapper.addMappings(new PropertyMap<WeighbridgeTicketEntity, WeighbridgeTicket>() {
            @Override
            protected void configure() {
                map(source.getWeighBridgeTicketId(), destination.getWeighBridgeTicketId());
                map(source.getLicensePlate(), destination.getLicensePlate());
                map(source.getSellerId(), destination.getSellerId());
                map(source.getStartWeight(), destination.getStartWeight());
                map(source.getEndWeight(), destination.getEndWeight());
                map(source.getStartTime() , destination.getStartTime());
                map(source.getEndTime() , destination.getEndTime());
                map(source.getMaterialType() , destination.getMaterialType());
            }
        });

        modelMapper.addMappings(new PropertyMap<Appointment, AppointmentEntity>() {
            @Override
            protected void configure() {
                map(source.getAppointmentId(), destination.getId());
                map(source.getMaterialType(), destination.getMaterialTypeEntity());
                map(source.getLicensePlate(), destination.getLicensePlate());
                map(source.getSellerId(), destination.getSellerId());
                map(source.getTime(), destination.getTime());
                map(source.getStatus() , destination.getStatus());
            }
        });

        modelMapper.addMappings(new PropertyMap<AppointmentEntity, Appointment>() {
            @Override
            protected void configure() {
                map(source.getId(), destination.getAppointmentId());
                map(source.getMaterialTypeEntity(), destination.getMaterialType());
                map(source.getTime(), destination.getTime());
                map(source.getLicensePlate(), destination.getLicensePlate());
                map(source.getSellerId(), destination.getSellerId());
                map(source.getStatus() , destination.getStatus());
            }
        });

        modelMapper.addMappings(new PropertyMap<Warehouse, WareHouseEntity>() {
            @Override
            protected void configure() {
                map(source.getWarehouseId(), destination.getWarehouseId());
                map(source.getSellerId(), destination.getSellerId());
                map(source.getMaterialType(), destination.getMaterialType());
                map(source.getAmountOfMaterial(), destination.getAmountOfMaterial());
            }
        });


        modelMapper.addMappings(new PropertyMap<WareHouseEntity, Warehouse>() {
            @Override
            protected void configure() {
                map(source.getWarehouseId(), destination.getWarehouseId());
                map(source.getSellerId(), destination.getSellerId());
                map(source.getMaterialType(), destination.getMaterialType());
                map(source.getAmountOfMaterial(), destination.getAmountOfMaterial());
            }
        });


        modelMapper.addMappings(new PropertyMap<Appointment, AppointmentQuery>() {
            @Override
            protected void configure() {
                map(source.getStatus(), destination.getAppointmentStatus());
                map(source.getMaterialType(), destination.getMaterialType());
                map(source.getTime(), destination.getTime());
                map(source.getSellerId(), destination.getSellerId());
                map(source.getLicensePlate(), destination.getLicensePlate());
            }
        });





        modelMapper.addConverter(uuidToSellerIdConverter);
        modelMapper.addConverter(sellerIdToUUIDConverter);
        modelMapper.addConverter(stringToLicensePlateConverter);
        modelMapper.addConverter(licensePlateToStringConverter);
        modelMapper.addConverter(uuidToSellerIdConverter);
        modelMapper.addConverter(sellerIdToUUIDConverter);



        return modelMapper;
    }
}
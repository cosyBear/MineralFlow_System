package be.kdg.prog6.LandSideBoundedContext.util.config;


import be.kdg.prog6.LandSideBoundedContext.adapters.out.entity.AppointmentEntity;
import be.kdg.prog6.LandSideBoundedContext.domain.Appointment;
import be.kdg.prog6.LandSideBoundedContext.domain.LicensePlate;
import be.kdg.prog6.LandSideBoundedContext.domain.SellerId;
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

        modelMapper.addConverter(uuidToSellerIdConverter);
        modelMapper.addConverter(sellerIdToUUIDConverter);
        modelMapper.addConverter(stringToLicensePlateConverter);
        modelMapper.addConverter(licensePlateToStringConverter);

        modelMapper.addMappings(new PropertyMap<Appointment, AppointmentEntity>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                map(source.getMaterialType(), destination.getMaterialTypeEntity());
                map(source.getLicensePlate(), destination.getLicensePlate());
                map(source.getSellerId(), destination.getSellerId());
                map(source.getTime(), destination.getTime());
            }
        });

        modelMapper.addMappings(new PropertyMap<AppointmentEntity, Appointment>() {
            @Override
            protected void configure() {
                map(source.getMaterialTypeEntity(), destination.getMaterialType());
                map(source.getTime(), destination.getTime());
                map(source.getLicensePlate(), destination.getLicensePlate());
                map(source.getSellerId(), destination.getSellerId());
            }
        });

        return modelMapper;
    }
}
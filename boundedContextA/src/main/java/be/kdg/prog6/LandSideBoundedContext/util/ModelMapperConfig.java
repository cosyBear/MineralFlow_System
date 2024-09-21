package be.kdg.prog6.LandSideBoundedContext.util;


import be.kdg.prog6.LandSideBoundedContext.adapters.out.entity.AppointmentEntity;
import be.kdg.prog6.LandSideBoundedContext.adapters.out.entity.TimeSlotEntity;
import be.kdg.prog6.LandSideBoundedContext.adapters.out.entity.TruckEntity;
import be.kdg.prog6.LandSideBoundedContext.domain.Appointment;
import be.kdg.prog6.LandSideBoundedContext.domain.TimeSlot;
import be.kdg.prog6.LandSideBoundedContext.domain.Truck;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // Appointment to AppointmentEntity Mapping
        modelMapper.addMappings(new PropertyMap<Appointment, AppointmentEntity>() {
            @Override
            protected void configure() {
                skip(destination.getId()); // Skip ID mapping since it's auto-generated
                map(source.getCompanyName() , destination.getCompanyName());
                map(source.getMaterialType(), destination.getMaterialTypeEntity()); // Map enums automatically
                map(source.getTruck(), destination.getTruck());
                map(source.getTimeSlot(), destination.getTimeSlot());
                map(source.getDate(), destination.getDate());
            }
        });

        // AppointmentEntity to Appointment Mapping
        modelMapper.addMappings(new PropertyMap<AppointmentEntity, Appointment>() {
            @Override
            protected void configure() {
                map(source.getMaterialTypeEntity(), destination.getMaterialType()); // Map enums automatically
                map(source.getTruck(), destination.getTruck());
                map(source.getTimeSlot(), destination.getTimeSlot());
                map(source.getDate(), destination.getDate());
            }
        });

        // Other mappings...
        configureTruckMappings(modelMapper);
        configureTimeSlotMappings(modelMapper);

        return modelMapper;
    }

    private void configureTruckMappings(ModelMapper modelMapper) {
        modelMapper.addMappings(new PropertyMap<Truck, TruckEntity>() {
            @Override
            protected void configure() {
                map(source.getLicenseNumber(), destination.getLicenseNumber());
                map(source.getPayload(), destination.getPayload());
            }
        });

        modelMapper.addMappings(new PropertyMap<TruckEntity, Truck>() {
            @Override
            protected void configure() {
                map(source.getLicenseNumber(), destination.getLicenseNumber());
                map(source.getPayload(), destination.getPayload());
            }
        });
    }

    private void configureTimeSlotMappings(ModelMapper modelMapper) {
        modelMapper.addConverter(new Converter<TimeSlotEntity, TimeSlot>() {
            @Override
            public TimeSlot convert(MappingContext<TimeSlotEntity, TimeSlot> context) {
                TimeSlotEntity source = context.getSource();
                return new TimeSlot(source.getEarliestArrivalTime(), source.getLatestArrivalTime());
            }
        });

        modelMapper.addConverter(new Converter<TimeSlot, TimeSlotEntity>() {
            @Override
            public TimeSlotEntity convert(MappingContext<TimeSlot, TimeSlotEntity> context) {
                TimeSlot source = context.getSource();
                return new TimeSlotEntity(source.earliestArravieTime(), source.latestArravieTime());
            }
        });

    }
}
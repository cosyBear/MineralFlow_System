package be.kdg.prog6.LandSideBoundedContext.util;


import be.kdg.prog6.LandSideBoundedContext.adapters.out.entity.AppointmentEntity;
import be.kdg.prog6.LandSideBoundedContext.domain.Appointment;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
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
                map(source.getMaterialType(), destination.getMaterialTypeEntity()); // Map enums automatically
                map(source.getLicensePlate(), destination.getLicensePlate());
                map(source.getSellerId(), destination.getSellerId());
                map(source.getDate(), destination.getDate());
            }
        });

        // AppointmentEntity to Appointment Mapping
        modelMapper.addMappings(new PropertyMap<AppointmentEntity, Appointment>() {
            @Override
            protected void configure() {
                map(source.getMaterialTypeEntity(), destination.getMaterialType()); // Map enums automatically
                map(source.getDate(), destination.getDate());
                map(source.getLicensePlate(), destination.getLicensePlate());
                map(source.getSellerId(), destination.getSellerId());
            }
        });

        return modelMapper;
    }


}
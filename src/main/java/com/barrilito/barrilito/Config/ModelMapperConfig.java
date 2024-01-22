package com.barrilito.barrilito.Config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        // Configuración de estrategia de mapeo (Matching Strategy)
        // Utilizando MatchingStrategies.STRICT para un mapeo estricto.
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        // Habilitar el mapeo de campos privados.
        modelMapper.getConfiguration().setFieldMatchingEnabled(true);
        // Pueden agregarse otras configuraciones según las necesidades de la aplicación.
        return modelMapper;
    }
}

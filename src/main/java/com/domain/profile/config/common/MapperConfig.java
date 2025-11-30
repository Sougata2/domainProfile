package com.domain.profile.config.common;

import com.domain.mapper.service.MapperService;
import com.domain.mapper.service.impl.MapperServiceImpl;
import com.domain.profile.mapper.Mapping;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public MapperService mapperService(Mapping mapping, EntityManager entityManager) {
        return new MapperServiceImpl(mapping, entityManager);
    }
}

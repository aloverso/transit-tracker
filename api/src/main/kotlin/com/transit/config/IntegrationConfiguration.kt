package com.transit.config

import com.transit.domain.ModesRepository
import com.transit.integrations.DefaultModesRepository
import com.transit.integrations.JpaModeRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile("!test")
class IntegrationConfiguration {

    @Bean
    fun getModesRepository(jpaModeRepository: JpaModeRepository): ModesRepository {
        return DefaultModesRepository(jpaModeRepository)
    }
}
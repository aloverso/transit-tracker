package com.transit.config

import com.transit.domain.AddModeUseCase
import com.transit.domain.GetModesUseCase
import com.transit.domain.ModesRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UseCaseConfiguration {

    @Bean
    fun getModesUseCase(modesRepository: ModesRepository): GetModesUseCase {
        return GetModesUseCase(modesRepository)
    }

    @Bean
    fun addModeUseCase(modesRepository: ModesRepository): AddModeUseCase {
        return AddModeUseCase(modesRepository)
    }
}
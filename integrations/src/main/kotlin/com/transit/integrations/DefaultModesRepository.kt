package com.transit.integrations

import com.transit.domain.ModesRepository
import com.transit.domain.TransitMode

class DefaultModesRepository(
    val jpaModeRepository: JpaModeRepository
): ModesRepository {

    override fun getModes(): List<TransitMode> {
        return jpaModeRepository.findAll().map {
            it.toDomain()
        }
    }

    override fun save(name: String): TransitMode {
        return jpaModeRepository.save(JpaTransitMode(
                name = name,
                counter = 1
        )).toDomain()
    }
}

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

    override fun update(mode: TransitMode): TransitMode {
        return jpaModeRepository.save(JpaTransitMode(
                id = mode.id,
                name = mode.name,
                counter = mode.counter
        )).toDomain()
    }

    override fun find(id: Int): TransitMode? {
        return jpaModeRepository.findById(id).orElse(null).toDomain()
    }
}
